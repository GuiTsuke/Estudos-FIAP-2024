package br.com.listaslazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.listaslazy.model.Game
import br.com.listaslazy.repository.getAllGames
import br.com.listaslazy.repository.getGamesByStudio
import br.com.listaslazy.ui.theme.ListasLazyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListasLazyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GamesScreen()
                }
            }
        }
    }
}

@Composable
fun GamesScreen() {

    var studioState by remember {
        mutableStateOf("")
    }

    var listGamesByStudioState by remember {
        mutableStateOf(getGamesByStudio(studioState))
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Meus jogos favoritos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = studioState,
            onValueChange = {
                studioState = it
                listGamesByStudioState = getGamesByStudio(studioState)
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome do estúdio")
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    listGamesByStudioState = getGamesByStudio(studioState)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Lupa de pesquisa"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(getAllGames()) {
                StudioCard(game = it, studioClick = { studio ->
                    studioState = studio
                    listGamesByStudioState = getGamesByStudio(studioState)
                })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(listGamesByStudioState) {
                GameCard(game = it)
            }
        }
    }
}

@Composable
fun StudioCard(game: Game, studioClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .height(35.dp)
            .padding(end = 4.dp)
            .width(100.dp)
            .clickable {
                studioClick(game.studio)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = game.studio)
        }
    }
}

    @Composable
    fun GameCard(game: Game) {
        Card(modifier = Modifier.padding(bottom = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(3f)
                ) {
                    Text(
                        text = game.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = game.studio,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Text(
                    text = game.releaseYear.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }
        }
    }

