package com.example.memory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size

@Composable
fun Jeux() {
    val fruits = listOf(
        R.drawable.apple,
        R.drawable.grape,
        R.drawable.dragon_fruit,
        R.drawable.lemon,
        R.drawable.litchi
    )

    val faceCachee = R.drawable.star // on vient mettre dans une variable l'image qui servira de face caché

    val numberOfCards = 4
    val states = remember {
        mutableStateListOf(*List(numberOfCards) { false }.toTypedArray())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            // On veut afficher 4 images (2 lignes de 2 images)
            for (row in 0 until 2) {
                Row {
                    for (col in 0 until 2) {
                        val index = row * 2 + col
                        val fruit = fruits[index]
                        val isFaceVisible = states[index]
                        Image(
                            painter = painterResource(id = if (isFaceVisible) fruit else faceCachee),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(RoundedCornerShape(22.dp))
                                .padding(8.dp)
                                .background(Color.LightGray)
                                .size(100.dp)
                                .clickable {
                                    states[index] = !states[index]
                                }
                        )
                    }
                }
            }
        }
    }
}
