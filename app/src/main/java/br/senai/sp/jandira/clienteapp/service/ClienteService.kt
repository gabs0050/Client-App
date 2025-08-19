package br.senai.sp.jandira.clienteapp.service

import br.senai.sp.jandira.clienteapp.model.Cliente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClienteService {

    @POST("clientes")
    fun gravar(@Body cliente: Cliente): Call<Cliente>

    @GET("clientes")
    fun exibirTodos(): Call<List<Cliente>>

    @PUT("clientes")
    fun atualizar(@Body cliente: Cliente): Call<Cliente>

    @HTTP(method = "DELETE", path = "clientes", hasBody = true)
    fun exculir(@Body cliente: Long?): Call<Unit>

    @GET("clientes/{id}")
    fun exibirPorId(@Path("id") id: Long): Call<Cliente>
}