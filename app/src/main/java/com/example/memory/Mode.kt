package com.example.memory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults


@Composable
fun Mode(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ){
        // bouton difficulté croissante
        Button(
            onClick = { /* rien pour le moemnt */ },
            colors = ButtonDefaults.buttonColors()
        ){
            Text(text = "difficulté croissante")
        }
    }
}