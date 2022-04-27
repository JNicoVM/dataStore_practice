package com.example.datastoreimplementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoreimplementation.dataStore.DataStoreAppImpl
import com.example.datastoreimplementation.protoDataStore.ProtoDataStoreImpl
import com.example.datastoreimplemntation.MessagePreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val dataStoreAppImpl: DataStoreAppImpl,
    private val protoDataStoreImpl: ProtoDataStoreImpl
) : ViewModel() {

    private var _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String>
        get() = _name
    private var _message: MutableLiveData<MessagePreference> = MutableLiveData()
    val message: LiveData<MessagePreference>
        get() = _message

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getNameFromPreference() {
        _isLoading.value = true
        viewModelScope.launch {
            _name.value = dataStoreAppImpl.getName() ?: "No se encontro algun valor"
            _isLoading.value = false
        }
    }

    fun getNameFromProto() {
        _isLoading.value = true
        viewModelScope.launch {
            _message.value = protoDataStoreImpl.read.first()
            _isLoading.value = false
        }
    }

    fun saveNameInPreferenceAndProto(name: String) {
        _isLoading.value = true
        viewModelScope.launch {
            dataStoreAppImpl.saveName(name)
            protoDataStoreImpl.saveName(name)
            _isLoading.value = false
        }
    }
}