package com.example.datastoreimplementation.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStoreAppImpl(private val context: Context) : DataStoreApp {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("app_preferences")

    override suspend fun saveName(name: String) {
        context.dataStore.edit { appPreferences ->
            appPreferences[nameKey] = name
        }
    }

    override suspend fun getName(): String? {
        val appPreferences = context.dataStore.data.first()
        return appPreferences[nameKey]
    }

}