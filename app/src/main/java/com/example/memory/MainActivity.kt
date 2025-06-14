package com.example.memory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memory.ui.theme.MemoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryTheme {
                val navController = rememberNavController() // Crée un NavController

                NavHost(navController = navController, startDestination = "accueil") {
                    composable("accueil") {
                        Accueil(onStartClick = {
                            navController.navigate("mode") // Navigation vers "mode" au clic
                        })
                    }
                    composable("mode") {
                        Mode(onStartClick = {
                            navController.navigate("jeux")
                        },
                            onTimerClick = {
                                navController.navigate("jeuxTimer")
                            },

                            onSupportClick = {
                                navController.navigate("Support")
                            },
                        )
                    }
                    composable("jeux") {
                        Jeux(navController = navController)
                    }

                    composable("jeuxTimer") {
                        JeuxTimer(navController = navController)
                    }

                    composable( "Support"){
                        Support()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JeuxPreview() {
    /*MemoryTheme {
        Jeux() // sans paramètre
    }*/
}

@Preview(showBackground = true)
@Composable
fun AccueilPreview() {
    MemoryTheme {
        Accueil(onStartClick = {})
        Mode(
            onStartClick = {},
            onTimerClick = {},
            onSupportClick = {}
        )
    }
}

