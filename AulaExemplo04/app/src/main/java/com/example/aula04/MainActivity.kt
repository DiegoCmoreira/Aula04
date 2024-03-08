package com.example.aula04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.aula04.ui.theme.Aula04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tela("Android")
                }
            }
        }
    }
}

@Composable
fun Tela(name: String, modifier: Modifier = Modifier) {
    val novaFruta = remember { mutableStateOf("") }
    val novoPreco = remember { mutableStateOf("") }
    val frutas = remember { mutableStateListOf<Fruta>() }

    val expandida = remember { mutableStateOf(false) }

    Column {
        TextButton(onClick = { expandida.value = !expandida.value }) {
            Text("Seleciona")
        }
        Box {
            DropdownMenu(
                expanded = expandida.value,
                onDismissRequest = { expandida.value = false },
            ) {
                DropdownMenuItem(text = { Text("Maçã") }, onClick = { })
                DropdownMenuItem(
                    text = { Text("Pera") },
                    onClick = {
                        novaFruta.value = "Pera"
                        expandida.value = false
                    }
                )
            }
        }

        if(novaFruta.value.isNotBlank()){
            Text("Vc selecionou ${novaFruta.value}")
        }
        /*
        Image(
            painter = painterResource(id = R.mipmap.lindo),
            contentDescription = "Nhathan",
            modifier = Modifier.fillMaxSize(0.5f)
        )
        Row {
            Image(
                painter = painterResource(id = R.mipmap.lindo),
                contentDescription = "Nhathan",
                modifier = Modifier.fillMaxWidth(0.33f)
            )
            Image(
                painter = painterResource(id = R.mipmap.lindo),
                contentDescription = "Nhathan",
                modifier = Modifier.fillMaxWidth(0.33f)
            )
            Image(
                painter = painterResource(id = R.mipmap.lindo),
                contentDescription = "Nhathan",
                modifier = Modifier.fillMaxWidth(0.33f)
            )
        }
         */

        TextField(
            value = novaFruta.value,
            onValueChange = { novaFruta.value = it },
            label = { Text("Nova fruta") },
            supportingText = {
                if (novaFruta.value.length < 3) {
                    Text(
                        "A nova fruta deve ter 3+ letras",
                        style = TextStyle(color = Color.Red)
                    )
                }
            }
        )

        TextField(
            value = novoPreco.value,
            onValueChange = { novoPreco.value = it },
            label = { Text("Novo preço") }
        )

        Button(onClick = {
            if (novaFruta.value.isNotBlank() && novoPreco.value.isNotBlank()) {
                frutas.add(Fruta(novaFruta.value, novoPreco.value.toDouble()))
            }
        }) {
            Text("Cadastrar")
        }

        LazyColumn {
            items(items = frutas) {
                Text("${it}")
            }
        }

        if (frutas.isNotEmpty()) {
            Text("${frutas.sumOf { it.preco }}")
        }

        /*
        frutas.forEach {
            Text(it)
        }
         */
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Aula04Theme {
        Tela("Android")
    }
}