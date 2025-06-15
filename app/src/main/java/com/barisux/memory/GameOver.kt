package com.barisux.memory
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.Column



@Composable
fun GameOver(
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Game Over",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { onRestart() }, // ne fait rien pour l’instant
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Recommencer")
            }

            Button(
                onClick = { onMenu() }, // ne fait rien pour l’instant
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "menu")
            }
        }
    }
}
