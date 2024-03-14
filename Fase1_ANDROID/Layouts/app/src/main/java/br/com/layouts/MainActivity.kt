package br.com.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.layouts.ui.theme.LayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.size(width = 200.dp, height = 200.dp),
                    color = Color.Cyan,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomEnd = 32.dp),
                    shadowElevation = 4.dp,
                    border = BorderStroke(width = 4.dp, color = Color.Gray)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "FIAP",
                            modifier = Modifier.align(Alignment.BottomCenter))
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.offset(x = 0.dp, y = (-30).dp)
                        ) {
                            Text(
                                text = "Clique-me"
                            )
                        }
                    }
                }
            }
        }
    }
}