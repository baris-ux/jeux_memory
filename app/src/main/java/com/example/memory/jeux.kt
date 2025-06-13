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

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size

@Composable
fun Jeux() {
    val baseFruits = listOf(
        R.drawable.apple,
        R.drawable.grape,
        R.drawable.dragon_fruit,
        R.drawable.lemon
    )

    val faceCachee = R.drawable.star
    val fruitPairs = remember { (baseFruits + baseFruits).shuffled() }

    val states = remember { mutableStateListOf(*List(fruitPairs.size) { false }.toTypedArray()) }
    val selectedIndices = remember { mutableStateListOf<Int>() }

    val columns = 4

    // 🕓 Gestion du délai si deux cartes sont sélectionnées
    LaunchedEffect(selectedIndices.size) {
        if (selectedIndices.size == 2) {
            delay(1000) // délai de 1 seconde (ajuste à 2000 ou 3000 si tu veux)
            val first = selectedIndices[0]
            val second = selectedIndices[1]
            if (fruitPairs[first] != fruitPairs[second]) {
                states[first] = false
                states[second] = false
            }
            selectedIndices.clear()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            for (row in fruitPairs.indices step columns) {
                Row {
                    for (col in 0 until columns) {
                        val index = row + col
                        if (index < fruitPairs.size) {
                            val isFaceVisible = states[index]
                            val fruit = fruitPairs[index]
                            Image(
                                painter = painterResource(id = if (isFaceVisible) fruit else faceCachee),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(22.dp))
                                    .padding(8.dp)
                                    .background(Color.LightGray)
                                    .size(100.dp)
                                    .clickable {
                                        // 👇 Logique de sélection
                                        if (!states[index] && selectedIndices.size < 2) {
                                            states[index] = true
                                            selectedIndices.add(index)
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}
