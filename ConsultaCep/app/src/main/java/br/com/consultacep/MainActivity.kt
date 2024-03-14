package br.com.consultacep

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.consultacep.model.Endereco
import br.com.consultacep.service.RetrofitFactory
import br.com.consultacep.ui.theme.ConsultaCepTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsultaCepTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CepScreen()
                }
            }
        }
    }
}

@Composable
fun CepScreen() {

    var cepState by remember { mutableStateOf("") }
    var ufState by remember { mutableStateOf("") }
    var cidadeState by remember { mutableStateOf("") }
    var ruaState by remember { mutableStateOf("") }

    var listaCepsState by remember {
        mutableStateOf(listOf<Endereco>())
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "CONSULTA CEP", fontSize = 24.sp)
                Text(
                    text = "Encontre o seu endereço",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = cepState,
                    onValueChange = {
                        cepState = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Qual o CEP procurado?")
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            var call = RetrofitFactory()
                                .getCepService()
                                .getEnderecoByCep(cep = cepState)

                            call.enqueue(object : Callback<Endereco>{
                                override fun onResponse(
                                    call: Call<Endereco>,
                                    response: Response<Endereco>
                                ) {
                                    listaCepsState = listOf(response.body()!!)
                                }

                                override fun onFailure(call: Call<Endereco>, t: Throwable) {
                                    Log.i("ERRO DE ONFAILURE DA REQUISICAO de CEP", "onFailure: ${t.message}")
                                }

                            })

                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = ""
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Não sabe o CEP?",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row{
                    OutlinedTextField(
                        value = ufState,
                        onValueChange = {
                            ufState = it
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        label = {
                            Text(text = "UF?")
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cidadeState,
                        onValueChange = {
                            cidadeState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "Qual a cidade?")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = ruaState,
                        onValueChange = {
                            ruaState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "O que lembra do nome da rua?")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )
                    IconButton(onClick = {
                        var call = RetrofitFactory()
                            .getCepService()
                            .getEnderecosByUfCidade(
                                uf = ufState,
                                cidade = cidadeState,
                                rua = ruaState
                            )

                        call.enqueue(object : Callback<List<Endereco>> {
                            override fun onResponse(
                                call: Call<List<Endereco>>,
                                response: Response<List<Endereco>>
                            ) {
                                listaCepsState = response.body()!!
                            }

                            override fun onFailure(call: Call<List<Endereco>>, t: Throwable) {
                                Log.i("ERRO DE ONFAILURE DA REQUISICAO", "onFailure: ${t.message}")
                            }

                        })
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn{
            items(listaCepsState) {
                CardEndereco(endereco = it)
            }
        }
    }
}

@Composable
fun CardEndereco(endereco: Endereco) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "CEP: ${endereco.cep}")
            Text(text = "Rua: ${endereco.rua}")
            Text(text = "Cidade: ${endereco.cidade}")
            Text(text = "Bairro: ${endereco.bairro}")
            Text(text = "UF: ${endereco.uf}")
        }
    }
}