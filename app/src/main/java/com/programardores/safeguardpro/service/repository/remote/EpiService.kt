package com.programardores.safeguardpro.service.repository.remote

import com.programardores.safeguardpro.service.model.Epi
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EpiService {
    @GET("selectEpi")
    suspend fun getEpis(): List<Epi>

    @Multipart
    @POST("addEpi")
    suspend fun createEpi(
        @Part("nome") nome: RequestBody,
        @Part("descrição") descricao: RequestBody,
        @Part("validade") validade: RequestBody
    ): Response<Epi>

    @GET("getEpi/{epi_id}")
    suspend fun getEpiById(@Path("epi_id") id: Int): Response<List<Epi>>

    @Multipart
    @PUT("updateEpi/{epi_id}")
    suspend fun updateEpi(
        @Path("epi_id") epiId: Int,
        @Part("nome") nome: RequestBody,
        @Part("descrição") descricao: RequestBody,
        @Part("validade") validade: RequestBody
    ): Response<Epi>

    @DELETE("deleteEpi/{epi_id}")
    suspend fun deleteEpiById(@Path("epi_id") id: Int): Response<Epi>
}