package com.example.datastoreimplementation.dataStore

interface DataStoreApp {
    suspend fun saveName(name : String)
    suspend fun getName() : String?
}