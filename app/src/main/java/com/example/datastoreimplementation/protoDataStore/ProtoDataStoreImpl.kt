package com.example.datastoreimplementation.protoDataStore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.datastoreimplemntation.MessagePreference
import kotlinx.coroutines.flow.catch
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ProtoDataStoreImpl(private val context: Context) : ProtoDataStore {

    private val Context.dataStore: DataStore<MessagePreference> by dataStore(
        fileName = "my_data",
        serializer = MessageSerializer
    )

    override val read: Flow<MessagePreference>
        get() = context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d("Error", exception.message.toString())
                emit(MessagePreference.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun saveName(name: String) {
        val currentIdPlus = context.dataStore.data.first().id + 1
        context.dataStore.updateData { preference ->
            preference.toBuilder().setName(name).setId(currentIdPlus).build()
        }
    }
}