package br.senai.sp.jandira.clienteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun formCliente(modifier: Modifier = Modifier) {
    
    //Variáveis de estado para utilizar o outlined
    var nomeCliente by remember {
        mutableStateOf("")
    }

    var emailCliente by remember {
        mutableStateOf("")
    }

    // Criar uma instância do RetrofitFactory
    val clienteApi = RetrofitFactory().getClienteService()

    Column (
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = nomeCliente,
            onValueChange = { nome ->
                nomeCliente = nome
            },
            label = {
                Text(text = "Digite seu nome")
            },
            supportingText = {
                Text(text = "Nome do cliente é obrigatório")
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = emailCliente,
            onValueChange = { email ->
                emailCliente = email
            },
            label = {
                Text(text = "Digite seu e-mail")
            },
            supportingText = {
                Text(text = "Email do cliente é obrigatório")
            },
            isError = true,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                // Criar um cliente com os dados que o usuário digitou
                val cliente = Cliente(
                    nome = nomeCliente,
                    email = emailCliente
                )
                // Requisição POST para a API
                GlobalScope.launch(Dispatchers.IO) {
                    val novoCliente = clienteApi.gravar(cliente).await()
                    println(novoCliente)
                }
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Gravar Cliente")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FormClientePreview(){
    formCliente()
}