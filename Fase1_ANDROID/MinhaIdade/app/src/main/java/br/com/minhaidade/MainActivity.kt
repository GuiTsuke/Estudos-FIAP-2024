package br.com.minhaidade

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.minhaidade.ui.theme.MinhaIdadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinhaIdadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MeuComponente()
                }
            }
        }
    }
}


@Composable
fun MeuComponente() {


    var idade = remember {
        mutableStateOf(1)
    }

    var txtMaiorIdade = remember {
        mutableStateOf("Menor")
    }

    fun testeIdade(idade: Int): String{
        if (idade >= 18){
            return "Maior"
        }else {
            return "Menor"
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Qual a sua idade?",
            color = Color(0xFFAD1F4E),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Aperte os botões até chegar na sua idade.",
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "${idade.value}",
            fontSize = 48.sp,
            color = Color(0xFFAD1F4E),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (idade.value > 1) {
                        idade.value--
                    }
                    txtMaiorIdade.value = testeIdade(idade.value)
                },
                modifier = Modifier.size(84.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAD1F4E))
            ) {
                Text(text = "-", fontSize = 40.sp)
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(
                onClick = {
                    if (idade.value < 130) {
                        idade.value++
                    }
                    txtMaiorIdade.value = testeIdade(idade.value)
                },
                modifier = Modifier.size(84.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAD1F4E)),

                ) {
                Text(text = "+", fontSize = 40.sp)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Você é ${txtMaiorIdade.value.uppercase()} de idade!",
            color = Color(0xFFAD1F4E),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeuComponentePreview() {
    MeuComponente()
}