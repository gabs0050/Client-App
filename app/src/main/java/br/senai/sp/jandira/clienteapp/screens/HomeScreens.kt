package br.senai.sp.jandira.clienteapp.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.clienteapp.R
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun HomeScreens(modifier: Modifier = Modifier){

    // Criar uma instância do retrofit factory
    val clienteApi = RetrofitFactory().getClienteService()

    // Criar uma variável de estado para armazenar a lista de clientes da API.
    var clientes by remember {
        mutableStateOf(listOf<Cliente>())
    }

    LaunchedEffect(Dispatchers.IO) {
        clientes = clienteApi.exibirTodos().await()
        println(clientes)
    }

    Scaffold(
        topBar = {
            BarraDeTiTulo()
        },
        bottomBar = {
            BarraDeNavegacao()
        },
        floatingActionButton = {
            BotaoFlutuante()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Row (
                modifier = Modifier
                    .padding(16.dp)
            )
            {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Ícone da lista de clientes",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lista de clientes"
                )
            }
            LazyColumn {
                items(clientes){ cliente ->
                    ClienteCard(cliente)
                }
            }
        }
    }
}

@Composable
fun ClienteCard(cliente: Cliente){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 4.dp
            ),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = cliente.nome,
                    fontWeight = FontWeight.Bold
                )
                Text(text = cliente.email,
                    fontSize = 12.sp
                )
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun ClienteCardPreview(){
    ClienteAppTheme {
        ClienteCard(Cliente())
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTiTulo (modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme
                    .colorScheme.primary
            ),
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Texto atoa",
                        fontSize = 18.sp,
                        color = MaterialTheme
                            .colorScheme.onPrimary
                    )
                    Text(
                        text = "email atoa",
                        fontSize = 16.sp,
                        color = MaterialTheme
                            .colorScheme.onPrimary
                    )
                }
                Card(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(6.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = "foto perfil"
                    )
                }

            }
        }
    )
}

@Preview
@Composable
private fun BarraDeTiTuloPreview() {
    BarraDeTiTulo()
}

@Composable
fun BarraDeNavegacao(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme
            .colorScheme.primary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme
                        .colorScheme.onPrimary
                )
            },
            label = {
                Text(text = "Home",
                    color = MaterialTheme
                        .colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = MaterialTheme
                        .colorScheme.onPrimary
                )
            },
            label = {
                Text(text = "Favorite",
                    color = MaterialTheme
                        .colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme
                        .colorScheme.onPrimary
                )
            },
            label = {
                Text(text = "Menu",
                    color = MaterialTheme
                        .colorScheme.onPrimary
                )
            }
        )
    }
}

@Preview
@Composable
private fun BarraDeNavegacaoPreview(){
    ClienteAppTheme {
        BarraDeNavegacao()
    }
}

@Composable
fun BotaoFlutuante(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme
            .colorScheme.tertiary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botão Adicionar",
            tint = MaterialTheme
                .colorScheme.onTertiary
        )
    }
}

@Preview
@Composable
private fun BotaoFlutuantePreview(){

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HomeScreensPreview(){
    ClienteAppTheme {
        HomeScreens()
    }
}