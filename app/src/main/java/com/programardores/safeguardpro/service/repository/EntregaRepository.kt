package com.programardores.safeguardpro.service.repository

import android.content.Context
import com.programardores.safeguardpro.service.model.Entrega
import com.programardores.safeguardpro.service.repository.remote.EntregaService
import com.programardores.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EntregaRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(EntregaService::class.java)

    private val entregaEmpty = Entrega(0,0, 0, "",0)

    suspend fun insertEntrega(entrega: Entrega): Entrega {
        return mRemote.createEntrega(
            epi_id = entrega.epi_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = entrega.funcionario_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            data_entrega = entrega.data_entrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            ca = entrega.ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            periodo = entrega.periodo.toRequestBody("text/plain".toMediaTypeOrNull()),
        ).body() ?: entregaEmpty
    }

    suspend fun getEntrega(id: Int): Entrega {
        val response = mRemote.getEntregaById(id)
        return if (response.isSuccessful){
            response.body()?.first() ?: entregaEmpty
        } else {
            entregaEmpty
        }
    }

    suspend fun getEntregas(): List<Entrega> {
        return  mRemote.getEntregas()
    }

    suspend fun updateEntrega(id: Int, entrega: Entrega): Entrega {
        return mRemote.updateEntrega(
            epi_id = entrega.epi_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = entrega.funcionario_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            data_entrega = entrega.data_entrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            ca = entrega.ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            periodo = entrega.periodo.toRequestBody("text/plain".toMediaTypeOrNull()),
            entregaId = id
        ).body() ?: entregaEmpty
    }

    suspend fun deleteEntrega(id: Int): Boolean {
        return mRemote.deleteEntregaById(id).isSuccessful
    }
}