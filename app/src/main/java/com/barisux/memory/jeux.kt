package com.barisux.memory
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
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.foundation.layout.fillMaxWidth


@Composable
fun Jeux(navController: NavController) {
    var resetKey by remember { mutableIntStateOf(0) }
    key(resetKey) {

        val allFruits = listOf(
            R.drawable.apple,
            R.drawable.grape,
            R.drawable.dragon_fruit,
            R.drawable.lemon,
            R.drawable.strawberry,
            R.drawable.orange
        )

        // 🆕 Base dynamique qu'on pourra modifier
        val baseFruits = remember { mutableStateListOf(R.drawable.apple, R.drawable.grape) }
        var essais by remember { mutableIntStateOf(5) }

        val faceCachee = R.drawable.star
        var fruitPairs by remember { mutableStateOf((baseFruits + baseFruits).shuffled()) }
        var states by remember { mutableStateOf(MutableList(fruitPairs.size) { false }) }
        val selectedIndices = remember { mutableStateListOf<Int>() }
        var bonusVisible by remember { mutableStateOf(false) }

        val columns = 2 // comme tu commences avec 4 cartes, 2 colonnes suffisent

        // ✅ Quand deux cartes sont sélectionnées, attendre, comparer, et vider
        LaunchedEffect(selectedIndices.size) {
            if (selectedIndices.size == 2) {
                val (first, second) = selectedIndices
                if (fruitPairs[first] != fruitPairs[second]) {
                    delay(1000)
                    states = states.toMutableList().also {
                        it[first] = false
                        it[second] = false
                        essais--
                    }
                }
                selectedIndices.clear()
            }
        }

        // ✅ Détecter victoire : toutes les cartes visibles
        LaunchedEffect(states) {
            if (states.all { it }) {
                // Attendre un peu avant de relancer une nouvelle manche
                delay(1000)

                // Ajouter un fruit nouveau depuis allFruits
                val unusedFruits = allFruits.filter { it !in baseFruits }
                if (unusedFruits.isNotEmpty()) {
                    val newFruit = unusedFruits.random()
                    baseFruits.add(newFruit)

                    // Recréer une nouvelle partie
                    fruitPairs = (baseFruits + baseFruits).shuffled()
                    states = MutableList(fruitPairs.size) { false }
                    selectedIndices.clear()
                }

                bonusVisible = true
                essais++
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
                        modifier = Modifier
                            .weight(1f)
                    )

                    Column( // ✅ Ceci regroupe essais et +1 essais verticalement
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "essais : $essais",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFb8b891),
                            textAlign = TextAlign.Right
                        )
                        if (bonusVisible) {
                            Text(
                                text = "+1 essais",
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
                                            enabled = essais > 0 && !states[index] && selectedIndices.size < 2
                                        ) {
                                            states =
                                                states.toMutableList().also { it[index] = true }
                                            selectedIndices.add(index)
                                        }
                                )
                            }
                        }
                    }
                }
            }
            if (essais <= 0) {
                GameOver(
                    onRestart = { resetKey++ },
                    onMenu = {
                        navController.navigate("mode") {
                            popUpTo("accueil") { inclusive = false } // facultatif : nettoie l'historique
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
