package com.programardores.safeguardpro.service.repository

import android.content.Context
import com.programardores.safeguardpro.service.model.Epi
import com.programardores.safeguardpro.service.repository.remote.EpiService
import com.programardores.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EpiRepository(context: Context) {

    private val mRemote = RetrofitClient.createService(EpiService::class.java)

    private val epiEmpty = Epi(0,"", "","")

    suspend fun insertEpi(epi: Epi): Epi {
        return mRemote.createEpi(
            nome = epi.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            descricao = epi.descricao.toRequestBody("text/plain".toMediaTypeOrNull()),
            validade = epi.validade.toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: epiEmpty
    }

    suspend fun getEpi(id: Int): Epi {
        val response = mRemote.getEpiById(id)
        return if (response.isSuccessful){
            response.body()?.first() ?: epiEmpty
        } else {
            epiEmpty
        }
    }

    suspend fun getEpis(): List<Epi> {
        return  mRemote.getEpis()
    }

    suspend fun updateEpi(id: Int, epi: Epi): Epi {
        return mRemote.updateEpi(
            nome = epi.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            descricao = epi.descricao.toRequestBody("text/plain".toMediaTypeOrNull()),
            validade = epi.validade.toRequestBody("text/plain".toMediaTypeOrNull()),
            epiId = id
        ).body() ?: epiEmpty
    }

    suspend fun deleteEpi(id: Int): Boolean {
        return mRemote.deleteEpiById(id).isSuccessful
    }
}