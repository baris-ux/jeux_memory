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

    val imageSize = 96.dp
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Utilisation de rememberUpdatedState pour garder la densité
    val densityState = rememberUpdatedState(density)

    val ghostCount = remember { (10..20).random() }

    val ghostOffsets = remember {
        List(ghostCount) { Animatable(-235f) }
    }

    val ghostYPositions = remember {
        List(ghostCount) { (50..300).random().dp }
    }

    // Animatable déclaré sans valeur initiale
    LaunchedEffect(Unit) {
        val screenWidthPx = with(densityState.value) { configuration.screenWidthDp.dp.toPx() }

        ghostOffsets.forEachIndexed { index, anim ->
            delay(index * 100L) // Décalage pour ne pas tous les lancer en même temps
            launch {
                anim.animateTo(
                    targetValue = screenWidthPx,
                    animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
                )
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFeb7a34)),
    ) {
        // Fantôme flottant
        ghostOffsets.forEachIndexed { index, offset ->
            Image(
                painter = painterResource(id = R.drawable.ghost),
                contentDescription = "image de fantome",
                modifier = Modifier
                    .offset(
                        x = with(density) { offset.value.toDp() },
                        y = ghostYPositions[index]
                    )
                    .size(imageSize)
            )
        }


        // UI centrale
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 80.dp),
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
    }
}
