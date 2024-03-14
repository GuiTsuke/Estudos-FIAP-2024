package com.example.estoque

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estoque.model.Produto
import com.example.estoque.repository.ProdutoRepository
import com.example.estoque.ui.theme.EstoqueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstoqueTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CadastroScreen()
                }
            }
        }
    }
}

@Composable
fun CadastroScreen() {

    val context = LocalContext.current
    val produtoRepository = ProdutoRepository(context)

    var listaProdutos by remember {
        mutableStateOf(produtoRepository.buscarTodos())
    }

    var nomeProdutoState by remember {
        mutableStateOf("")
    }

    var quantidadeState by remember {
        mutableStateOf("")
    }

    var dataVencimentoState by remember {
        mutableStateOf("")
    }

    var disponivelState by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cadastro de produtos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = nomeProdutoState,
            onValueChange = {
                nomeProdutoState = it
            },
            label = { Text(text = "Nome do produto") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
        )
        OutlinedTextField(
            value = quantidadeState,
            onValueChange = {
                quantidadeState = it
            },
            label = { Text(text = "Quantidade em estoque") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = dataVencimentoState,
            onValueChange = {
                dataVencimentoState = it
            },
            label = { Text(text = "Data de vencimento") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = disponivelState,
                onCheckedChange = {
                    disponivelState = it
                }
            )
            Text(text = "Disponível")
        }
        Button(
            onClick = {
                val produto = Produto(
                    nome = nomeProdutoState,
                    quantidade = quantidadeState.toInt(),
                    dataVencimento = dataVencimentoState,
                    disponivel = disponivelState
                )

                produtoRepository.salvar(produto)
                listaProdutos = produtoRepository.buscarTodos()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(listaProdutos) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 8.dp)
                ) {
                    Column {
                        Text(text = "Nome: ${it.nome}")
                        Text(text = "Qtd: ${it.quantidade}")
                        Text(text = "Data de Vencimento: ${it.dataVencimento}")
                        var disp = "Não"
                        if (it.disponivel)
                        {
                            disp = "Sim"
                        }
                        Text(text = "Disponivel: ${disp}")
                    }
                }
            }
        }
    }
}