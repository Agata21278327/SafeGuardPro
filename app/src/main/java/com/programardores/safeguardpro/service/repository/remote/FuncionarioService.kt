package com.programardores.safeguardpro.service.repository.remote

import com.programardores.safeguardpro.service.model.Funcionario
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface FuncionarioService {
    @GET("selectFuncionario")
    suspend fun getFuncionarios(): List<Funcionario>

    @Multipart
    @POST("addFuncionario")
    suspend fun createFuncionario(
        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @GET("getFuncionario/{funcionario_id}")
    suspend fun getFuncionarioById(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("getFuncionario/{funcionario_cpf}")
    suspend fun getFuncionarioByCpf(@Path("funcionario_ca") cpf: String): Response<List<Funcionario>>

    @Multipart
    @PUT("updateFuncionario/{funcionario_id}")
    suspend fun updateFuncionario(
        @Path("funcionario_id") funcionarioId: Int,
        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @DELETE("deleteFuncionario/{funcionario_id}")
    suspend fun deleteFuncionarioById(@Path("funcionario_id") id: Int): Response<Funcionario>

}