package br.com.entradadousuario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.entradadousuario.ui.theme.EntradaDoUsuarioTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntradaDoUsuarioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var nome by remember {
        mutableStateOf("")
    }
    var idade by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var selecione by remember {
        mutableStateOf(false)
    }

    var linguagemSelected by remember {
        mutableStateOf(-1)
    }

    Column(modifier = Modifier.padding(32.dp)) {
        TextField(
            value = nome,
            onValueChange = { letra ->
                nome = letra
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Qual seu nome?")
            },
            placeholder = {
                Text(text = "Ex.: Guilherme Borges")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Icone de pessoa"
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Icone de pessoa"
                )
            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)

        )
        TextField(
            value = idade,
            onValueChange = { letra ->
                idade = letra
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            label = {
                Text(text = "Qual sua Idade?")
            },
            placeholder = {
                Text(text = "Ex.: 22")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Icone de pessoa"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray
            )
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            label = {
                Text(text = "Qual seu e-mail?")
            },
            placeholder = {
                Text(text = "Ex.: xxxx@fiap.com.br")
            },
            singleLine = true,
            shape = RoundedCornerShape(32.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.Gray
            )
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = selecione, onCheckedChange = { selecione = it })
            Text(text = "Java")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text(text = "C#")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text(text = "Kotlin")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = linguagemSelected == 0,
                onClick = {
                    linguagemSelected = 0
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Cyan,
                    unselectedColor = Color.Gray
                )
            )
            Text(text = "Windows")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = linguagemSelected == 1,
                onClick = {
                    linguagemSelected = 1
                }
            )
            Text(text = "Mac")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = linguagemSelected == 2,
                onClick = {
                    linguagemSelected = 2
                }
            )
            Text(text = "Linux")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xF1234564),
                    contentColor = Color.White
                )
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    Text(text = "Clique-me")
                }

            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    EntradaDoUsuarioTheme {
//        Greeting()
//    }
//}