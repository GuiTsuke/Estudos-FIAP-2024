package br.com.meuscontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.meuscontatos.database.repository.ContatoRepository
import br.com.meuscontatos.model.Contato
import br.com.meuscontatos.ui.theme.MeusContatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeusContatosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ContatosScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun ContatosScreen() {

    var idState = remember {
        mutableLongStateOf(0)
    }

    var nomeState = remember {
        mutableStateOf("")
    }

    var telefoneState = remember {
        mutableStateOf("")
    }

    var amigoState = remember {
        mutableStateOf(false)
    }

    var cadastroCompleted by remember {
        mutableLongStateOf(0)
    }

    val context = LocalContext.current
    val contatoRepository = ContatoRepository(context)

    var listaContatosState = remember {
        mutableStateOf(contatoRepository.listarContatos())
    }

    var isAtualizar = remember {
        mutableStateOf(false)
    }



    Column {
        ContatoForm(
            id = idState.longValue,
            nome = nomeState.value,
            telefone = telefoneState.value,
            amigo = amigoState.value,
            onNomeChange = {
                nomeState.value = it
            },
            onTelefoneChange = {
                telefoneState.value = it
            },
            onAmigoChange = {
                amigoState.value = it
            },
            onCadastroChange = {
                cadastroCompleted = it
            },
            atualizar = {
                listaContatosState.value = contatoRepository.listarContatos()
            },
            isAtualizar = isAtualizar.value
        )
        ContatoList(
            listaContatosState,
            atualizar = {
                listaContatosState.value = contatoRepository.listarContatos()
            },
            listarContato = {
                idState.longValue = it.id
                nomeState.value = it.nome
                telefoneState.value = it.telefone
                amigoState.value = it.isAmigo
                isAtualizar.value = true
            }
        )
    }
}

@Composable
fun ContatoForm(
    id: Long,
    nome: String,
    telefone: String,
    amigo: Boolean,
    onNomeChange: (String) -> Unit,
    onTelefoneChange: (String) -> Unit,
    onAmigoChange: (Boolean) -> Unit,
    onCadastroChange: (Long) -> Unit,
    atualizar: () -> Unit,
    isAtualizar: Boolean
) {
    //Obter contexto
    val context = LocalContext.current
    val contatoRepository = ContatoRepository(context)


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cadastro de contatos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(
                0xFFE91E63
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nome,
            onValueChange = { onNomeChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome do contato")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = telefone,
            onValueChange = { onTelefoneChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Telefone do contato")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = amigo, onCheckedChange = {
                onAmigoChange(it)
            })
            Text(text = "Amigo")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (!isAtualizar) {
            Button(
                onClick = {
                    val contato = Contato(
                        id = 0,
                        nome = nome,
                        telefone = telefone,
                        isAmigo = amigo
                    )

                    onCadastroChange(contatoRepository.salvar(contato))
                    atualizar()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "CADASTRAR",
                    modifier = Modifier.padding(8.dp)
                )
            }
        } else {
            Button(
                onClick = {
                    val contato = Contato(
                        id = id,
                        nome = nome,
                        telefone = telefone,
                        isAmigo = amigo
                    )
                    contatoRepository.atualizar(contato)
                    atualizar()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ATUALIZAR",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ContatoList(
    listaContatos: MutableState<List<Contato>>,
    atualizar: () -> Unit,
    listarContato: (Contato) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (contato in listaContatos.value) {
            ContatoCard(contato, atualizar, listarContato)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ContatoCard(contato: Contato, atualizar: () -> Unit, listarContato: (Contato) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                listarContato(contato)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        val context = LocalContext.current
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            ) {
                Text(
                    text = contato.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = contato.telefone,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (contato.isAmigo) "Amigo" else "Conhecido",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = {
                val contatoRepository = ContatoRepository(context)
                contatoRepository.excluir(contato)
                atualizar()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}