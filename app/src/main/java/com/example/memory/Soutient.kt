package com.example.memory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp // ✅ pour fontSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Support() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFeb7a34)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Offre-nous un café ☕ en regardant une pub (merci !)",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
