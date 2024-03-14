package br.com.mytrips.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mytrips.R

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.End),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFCC0000)),
            shape = RoundedCornerShape(bottomStart = 16.dp)
        ) {

        }
        Card(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokebola_logo),
                contentDescription = "Pokebola do pokemon",
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier.padding(start = 32.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCC0000)
            )
            Text(
                text = "Faça login para continuar",
                fontSize = 14.sp,
                color = Color(0xFF8A4848)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Icone de usuario", tint = Color(0xFFCC0000))
                },
                label = {
                    Text(text = "Nome de Usuário")
                },
                placeholder = {
                    Text(text = "Digite seu nome de usuário")
                }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Icone de cadeado", tint = Color(0xFFCC0000))
                },
                label = {
                    Text(text = "Senha")
                },
                placeholder = {
                    Text(text = "Digite sua senha")
                }
            )
            Button(
                onClick = {
                    var nome = "Guilherme"
                    navController.navigate("signup?nome=${nome}")
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(Color(0xFFCC0000))
            ) {
                Text(text = "ENTRAR",
                    fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "setinha de continuar"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "Não possui conta?")
                Text(
                    text = "Cadastrar",
                    modifier = Modifier.padding(start = 4.dp),
                    color = Color(0xFFCC0000)
                )
            }
        }
        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 40.dp)
                .align(Alignment.Start),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFCC0000)),
            shape = RoundedCornerShape(topEnd = 16.dp)
        ) {

        }
    }
}