package com.barisux.memory
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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon

@Composable
fun Mode(
    onStartClick: () -> Unit,
    onTimerClick: () -> Unit,
    onSupportClick: () -> Unit,
    onSequenceClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f)) // espace haut

            // bouton difficulté croissante
            Button(
                onClick = onStartClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.increase),
                    contentDescription = "icone bouton",
                    modifier = Modifier.height(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Difficulté croissante")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // bouton contre la montre
            Button(
                onClick = onTimerClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.timer),
                    contentDescription = "icone bouton",
                    modifier = Modifier.height(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Contre la montre")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // bouton nouveau mode
            Button(
                onClick = onSequenceClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1),
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.word),
                    contentDescription = "icone bouton",
                    modifier = Modifier.height(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "séquence")
            }

            Spacer(modifier = Modifier.weight(1f)) // espace bas avant le bouton don

            // bouton don
            Button(
                onClick = onSupportClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFfa4147),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "icone cœur",
                    modifier = Modifier.height(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Clique ici pour m'aider")
            }

            Spacer(modifier = Modifier.height(48.dp)) // marge avec le bas de l’écran
        }
    }
}
//commentaire