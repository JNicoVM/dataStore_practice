package com.example.datastoreimplementation.di

import com.example.datastoreimplementation.MainActivityViewModel
import com.example.datastoreimplementation.dataStore.DataStoreApp
import com.example.datastoreimplementation.dataStore.DataStoreAppImpl
import com.example.datastoreimplementation.protoDataStore.ProtoDataStoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { MainActivityViewModel(get(), get()) }
}

val databaseModule = module {
    single{ DataStoreAppImpl(androidContext()) }
    single{ ProtoDataStoreImpl(androidContext())}
}