package com.barisux.memory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun CompteBon() {
    var nombre by remember { mutableIntStateOf(0) }
    val animationTerminee = remember { mutableStateOf (false)}
    val imageSize = 96.dp
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val countdown = remember { mutableIntStateOf(5) }
    val countdownover = remember { mutableStateOf ( false )}

    // Utilisation de rememberUpdatedState pour garder la densité
    val densityState = rememberUpdatedState(density)

    val imageCount = remember { (10..20).random() } // c'est le nombre d'image aléatoire qui va défiler

    val imageOffsets = remember {
        List(imageCount) { Animatable(-250f) }
    }

    val imageYPositions = remember {
        List(imageCount) { (50..300).random().dp }
    }

    val allimages = listOf(
        R.drawable.ghost
    )
    val selectedImages = remember {
        allimages.random()
    }


    // Animatable déclaré sans valeur initiale
    LaunchedEffect(Unit) {
        val screenWidthPx = with(densityState.value) { configuration.screenWidthDp.dp.toPx() }

        imageOffsets.forEachIndexed { index, anim ->
            delay(index * 80L) // Décalage pour ne pas tous les lancer en même temps
            launch {
                anim.animateTo(
                    targetValue = screenWidthPx,
                    animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
                )
            }
        }
        delay((imageOffsets.size * 80L) + 3000L)
        animationTerminee.value = true
    }

    LaunchedEffect(animationTerminee.value) {
        if (animationTerminee.value) {
            while (countdown.intValue > 0) {
                delay(1000L)
                countdown.value -= 1
            }
            countdownover.value = true

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFeb7a34)),
    ) {
        // Fantômes
        imageOffsets.forEachIndexed { index, offset ->
            Image(
                painter = painterResource(id = selectedImages),
                contentDescription = "image de fantome",
                modifier = Modifier
                    .offset(
                        x = with(density) { offset.value.toDp() },
                        y = imageYPositions[index]
                    )
                    .size(imageSize)
            )
        }

        // UI du bas
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "nombre : $nombre",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { nombre++ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4169E1))
            ) {
                Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { if (nombre > 0) nombre-- },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4169E1))
            ) {
                Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        if (animationTerminee.value && !countdownover.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${countdown.intValue}",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(0xAA000000))
                        .padding(32.dp)
                )
            }
        }

        if (countdownover.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color(0xAA000000))
                        .padding(24.dp)
                ) {
                    Text(
                        text = "réponse : $imageCount",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (nombre == imageCount) "Bonne réponse !" else "Mauvaise réponse",
                        color = if (nombre == imageCount) Color.Green else Color.Red,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
