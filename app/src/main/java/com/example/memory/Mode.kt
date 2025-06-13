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

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width

@Composable
fun Mode(onStartClick: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        contentAlignment = Alignment.Center
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally){

            // bouton difficulté croissante
            Button(
                onClick = onStartClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                )
            ){
                Image(
                    painter = painterResource(id = R.drawable.increase),
                    contentDescription = "icone bouton",
                    modifier = Modifier
                        .height(36.dp) // ajuste la taille si nécessaire
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "contre la montre")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // bouton contre la montre
            Button(
                onClick = { /* on ajoutera plus tard */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                )

            ){
                Image(
                    painter = painterResource(id = R.drawable.timer),
                    contentDescription = "icone bouton",
                    modifier = Modifier
                        .height(36.dp) // ajuste la taille si nécessaire
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "contre la montre")
            }
        }
    }
}
//commentaire