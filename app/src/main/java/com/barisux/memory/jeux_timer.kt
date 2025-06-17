package com.barisux.memory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun JeuxTimer(navController: NavController) {
    var resetKey by remember { mutableIntStateOf(0) }
    var roundKey by remember { mutableIntStateOf(0) }

    key(resetKey) {
        val timeLeft = remember { mutableIntStateOf(30) }

        LaunchedEffect(Unit) {
            while (timeLeft.intValue > 0) {
                delay(1000)
                timeLeft.intValue--
            }
        }

        val allFruits = listOf(
            R.drawable.apple,
            R.drawable.grape,
            R.drawable.dragon_fruit,
            R.drawable.lemon,
            R.drawable.strawberry,
            R.drawable.orange
        )

        val baseFruits = remember { mutableStateListOf(R.drawable.apple, R.drawable.grape) }

        val faceCachee = R.drawable.star
        var fruitPairs by remember { mutableStateOf((baseFruits + baseFruits).shuffled()) }
        var states by remember { mutableStateOf(MutableList(fruitPairs.size) { false }) }
        val selectedIndices = remember { mutableStateListOf<Int>() }
        var bonusVisible by remember { mutableStateOf(false) }
        var showAllCardsTemporarily by remember { mutableStateOf(true) }

        val columns = 2

        suspend fun startNewRound() {
            fruitPairs = (baseFruits + baseFruits).shuffled()
            states = MutableList(fruitPairs.size) { true }
            selectedIndices.clear()
            showAllCardsTemporarily = true
            delay(2000)
            states = MutableList(fruitPairs.size) { false }
            showAllCardsTemporarily = false
        }

        LaunchedEffect(resetKey, roundKey) {
            startNewRound()
        }

        LaunchedEffect(selectedIndices.size) {
            if (selectedIndices.size == 2) {
                val (first, second) = selectedIndices
                if (fruitPairs[first] != fruitPairs[second]) {
                    delay(1000)
                    states = states.toMutableList().also {
                        it[first] = false
                        it[second] = false
                    }
                }
                selectedIndices.clear()
            }
        }

        LaunchedEffect(states) {
            if (!showAllCardsTemporarily && states.all { it }) {
                delay(1000)
                bonusVisible = true
                timeLeft.value += 5

                val unusedFruits = allFruits.filter { it !in baseFruits }
                if (unusedFruits.isNotEmpty()) {
                    baseFruits.add(unusedFruits.random())
                }

                roundKey++ // Relance la manche suivante
            }
        }

        LaunchedEffect(bonusVisible) {
            if (bonusVisible) {
                delay(1000)
                bonusVisible = false
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5DC)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Round : ${baseFruits.size - 1}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFb8b891),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f)
                    )

                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "temps restant : ${timeLeft.intValue}s",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFb8b891),
                            textAlign = TextAlign.Right
                        )
                        if (bonusVisible) {
                            Text(
                                text = "+5 secondes",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF00AA00),
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

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
                                        .padding(12.dp)
                                        .clip(RoundedCornerShape(22.dp))
                                        .background(Color.LightGray)
                                        .size(100.dp)
                                        .clickable(
                                            enabled = timeLeft.intValue > 0 && !states[index] && selectedIndices.size < 2
                                        ) {
                                            states = states.toMutableList().also { it[index] = true }
                                            selectedIndices.add(index)
                                        }
                                )
                            }
                        }
                    }
                }
            }

            if (timeLeft.intValue <= 0) {
                GameOver(
                    onRestart = { resetKey++ },
                    onMenu = {
                        navController.navigate("mode") {
                            popUpTo("accueil") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
