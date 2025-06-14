package com.example.memory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay



@Composable
fun Sequence() {
    val sequence =  remember { mutableStateListOf<Int>()}
    val userInput = remember { mutableStateListOf<Int>()}
    val buttonColors = remember { mutableStateListOf<Color>().apply { repeat(9) { add(Color.Gray) } } }
    val totalButtons = 9

    val flashColors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Cyan, Color.Magenta,
        Color.DarkGray, Color.Black, Color.White
    )


    LaunchedEffect(Unit) {
        sequence.clear()
        userInput.clear()
        val newSequence = List(3) { (0 until totalButtons).random() }
        sequence.addAll(newSequence)
        for (index in sequence) {
            buttonColors[index] = flashColors[index]
            delay(500)
            buttonColors[index] = Color.Gray
            delay(300)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFeb7a34)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), // carré global
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(3) { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // hauteur égale pour chaque ligne
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) { col ->
                        val index = row * 3 + col

                        Button(
                            onClick = {
                                // action du bouton
                            },
                            modifier = Modifier
                                .weight(1f) // largeur égale
                                .aspectRatio(1f)
                                .padding(4.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 30.dp,
                                pressedElevation = 2.dp

                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColors[index]
                            )

                        ) {
                            //Text(text = "${row * 3 + col + 1}")
                        }
                    }
                }
            }
        }
    }
}
