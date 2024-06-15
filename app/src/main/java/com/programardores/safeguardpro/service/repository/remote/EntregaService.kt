package com.programardores.safeguardpro.service.repository.remote

import com.programardores.safeguardpro.service.model.Entrega
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EntregaService {
    @GET("selectEntrega")
    suspend fun getEntregas(): List<Entrega>

    @Multipart
    @POST("addEntrega")
    suspend fun createEntrega(
        @Part("epi_id") epi_id: RequestBody,
        @Part("funcionario_id") funcionario_id: RequestBody,
        @Part("data_entrega") data_entrega: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("periodo") periodo: RequestBody
    ): Response<Entrega>

    @GET("getEntrega/{entrega_id}")
    suspend fun getEntregaById(@Path("entrega_id") id: Int): Response<List<Entrega>>

    @Multipart
    @PUT("updateEntrega/{entrega_id}")
    suspend fun updateEntrega(
        @Path("entrega_id") entregaId: Int,
        @Part("epi_id") epi_id: RequestBody,
        @Part("funcionario_id") funcionario_id: RequestBody,
        @Part("data_entrega") data_entrega: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("periodo") periodo: RequestBody
    ): Response<Entrega>

    @DELETE("deleteEntrega/{entrega_id}")
    suspend fun deleteEntregaById(@Path("entrega_id") id: Int): Response<Entrega>
}