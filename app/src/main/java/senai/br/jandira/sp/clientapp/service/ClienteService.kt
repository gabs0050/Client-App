package senai.br.jandira.sp.clientapp.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import senai.br.jandira.sp.clientapp.model.Cliente

interface ClienteService {

    @POST("clientes")
    fun gravar(@Body cliente: Cliente): Call<Cliente>

    @GET("clientes")
    fun exibirTodos(): Call<List<Cliente>>

    @PUT("clientes")
    fun atualizar(@Body cliente: Cliente): Call<Cliente>

    @DELETE("clientes")
    fun excluir(@Path ("id") id: Long): Call<Unit>

    @GET("clientes/{id}")
    fun exibirPorId(@Path("id") id: Long): Call<Cliente>

}