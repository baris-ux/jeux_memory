package com.example.memory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.memory.ui.theme.MemoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryTheme {
                // Écran d'accueil avec un bouton "Commencer"
                Accueil(onStartClick = {
                    // Action à faire plus tard (ex : navigation)
                    // Pour l'instant, ne fait rien
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccueilPreview() {
    MemoryTheme {
        Accueil(onStartClick = {})
    }
}
