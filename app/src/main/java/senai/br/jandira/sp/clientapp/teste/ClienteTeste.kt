package senai.br.jandira.sp.clientapp.teste

import senai.br.jandira.sp.clientapp.model.Cliente
import senai.br.jandira.sp.clientapp.service.RetrofitFactory

fun main () {

    val c1 = Cliente(
        nome = "Pelézinho",
        email = "pele@santosfc.com.br"
    )

    val retrofit = RetrofitFactory().getClienteService()
    val cliente = retrofit.gravar((c1))

}