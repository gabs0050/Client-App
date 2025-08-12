package br.senai.sp.jandira.clienteapp.teste

import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory

fun main(){
    val c1 = Cliente(
        nome = "Mohammad",
        email = "mohammad@gmail.com"
    )

    val retrofit = RetrofitFactory().getClienteService()
    val cliente = retrofit.gravar(c1)
}