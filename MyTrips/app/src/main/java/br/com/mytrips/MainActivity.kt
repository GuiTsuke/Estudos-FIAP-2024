package br.com.mytrips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.mytrips.screens.LoginScreen
import br.com.mytrips.screens.PerfilScreen
import br.com.mytrips.screens.SignUpScreen
import br.com.mytrips.ui.theme.MyTripsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTripsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.End,
                                tween(2000)
                            ) + fadeOut(animationSpec = tween(2000))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                tween(2000)
                            )
                        }
                    ) {
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "signup?nome={nome}") {
                            val nome = it.arguments?.getString("nome")
                            SignUpScreen(navController, nome!!)
                        }
                        composable(route = "perfil/{usuario}/{senha}", arguments = listOf(
                            navArgument(
                                name = "usuario"
                            ) {
                                type = NavType.StringType
                            },
                            navArgument(name = "senha") {
                                type = NavType.StringType
                            }

                        )) {
                            val usuario = it.arguments?.getString("usuario")
                            val senha = it.arguments?.getString("senha")
                            PerfilScreen(navController, usuario!!, senha!!)
                        }
                    }
                }
            }
        }
    }
}
