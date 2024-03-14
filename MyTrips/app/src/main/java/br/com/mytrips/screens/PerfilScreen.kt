package br.com.mytrips.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PerfilScreen(navController: NavController, usuario: String, senha: String) {
    Column {
        Text(text = "Olá, ${usuario}, sua senha é $senha")
        Button(onClick = {
            navController.navigate("login")
        }) {
            Text(text = "Voltar")
        }
    }
}