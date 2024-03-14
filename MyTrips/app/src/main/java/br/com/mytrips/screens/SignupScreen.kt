package br.com.mytrips.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SignUpScreen(navController: NavController, nome: String) {
    Column {
        Text(text = "Olá, ${nome}")
        Button(onClick = {
            navController.navigate("perfil/Guilherme/Guigui")
        }) {
            Text(text = "Perfil")
        }
        Button(onClick = {
            navController.navigate("login")
        }) {
            Text(text = "Voltar")
        }
    }
}