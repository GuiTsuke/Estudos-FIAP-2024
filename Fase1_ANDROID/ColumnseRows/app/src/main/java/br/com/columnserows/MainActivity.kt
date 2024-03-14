package br.com.columnserows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.columnserows.ui.theme.ColumnsERowsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColumnsERowsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Green
                ) {
                    LayoutScreen()
                }
            }
        }
    }
}


@Composable
fun LayoutScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Button 1")
        }
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Button 2")
        }
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Button 3")
        }
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxHeight()
        ) {
            Text(text = "FIAP")
            Text(text = "ANDROID")
            Text(text = "ANDROID STUDIO")
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .background(Color.Magenta)
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Botão 4")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Botão 5")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Botão 6")
                }
                Column {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Botão 7")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Botão 8")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Botão 9")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Botão 1")
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .background(Color.Magenta)
                    .fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Botão 4")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Botão 5")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Botão 6")
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LayoutScreenPreview() {
    LayoutScreen()
}