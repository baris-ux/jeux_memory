package com.example.memory
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


@Composable
fun GameOver() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // 🖤 fond noir
        contentAlignment = Alignment.Center // centre le contenu
    ) {
        Text(
            text = "Game Over", // ✍️ ton message
            color = Color.White, // 🤍 texte blanc
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}