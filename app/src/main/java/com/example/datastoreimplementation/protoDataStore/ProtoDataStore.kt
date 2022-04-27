package com.example.datastoreimplementation.protoDataStore

import com.example.datastoreimplemntation.MessagePreference
import kotlinx.coroutines.flow.Flow

interface ProtoDataStore {
    val read : Flow<MessagePreference>
    suspend fun saveName(name : String)
}