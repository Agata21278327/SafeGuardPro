package com.programardores.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.programardores.safeguardpro.service.model.Entrega
import com.programardores.safeguardpro.service.repository.EntregaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntregaCadastroViewModel(application: Application): AndroidViewModel(application) {
    private val repository = EntregaRepository(application)

    private val mEntrega = MutableLiveData<Entrega>()
    val entrega: LiveData<Entrega> = mEntrega

    private val mEntregaList = MutableLiveData<List<Entrega>>()
    val entregaList: LiveData<List<Entrega>> = mEntregaList

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mCreatedEntrega = MutableLiveData<Entrega>()
    val createdEntrega: LiveData<Entrega> = mCreatedEntrega

    private val mUpdateEntrega = MutableLiveData<Entrega>()
    val updateEntrega: LiveData<Entrega> = mUpdateEntrega

    private val mDeletedEntrega = MutableLiveData<Boolean>()
    val deletedEntrega: LiveData<Boolean> = mDeletedEntrega

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEntregaList.postValue(repository.getEntregas())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(entrega: Entrega){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEntrega = repository.insertEntrega(entrega)
                mCreatedEntrega.postValue(createdEntrega)
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun getEntrega(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEntrega.postValue(repository.getEntrega(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun update(entrega: Entrega){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedEntrega = repository.updateEntrega(entrega.id, entrega)
                mUpdateEntrega.postValue(updatedEntrega)
            } catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedEntrega.postValue(repository.deleteEntrega(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}