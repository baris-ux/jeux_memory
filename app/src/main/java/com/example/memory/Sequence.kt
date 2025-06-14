package com.example.memory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.navigation.NavController // ✅ important

@Composable
fun Sequence(navController: NavController) {
    var resetKey by remember { mutableIntStateOf(0) }
    val sequence = remember { mutableStateListOf<Int>() }
    val userInput = remember { mutableStateListOf<Int>() }
    val buttonColors = remember { mutableStateListOf<Color>().apply { repeat(9) { add(Color.Gray) } } }
    val totalButtons = 9
    val scope = rememberCoroutineScope()
    var resultat = remember { mutableStateOf("") }
    var isSequencePlaying by remember { mutableStateOf(true)}
    var level by remember { mutableIntStateOf (1)}

    val flashColors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Cyan, Color.Magenta,
        Color.DarkGray, Color.Black, Color.White
    )

    // Affichage de la séquence
    LaunchedEffect(resetKey) {
        resultat.value = ""
        sequence.clear()
        userInput.clear()
        isSequencePlaying = true

        val newSequence = List(level) { (0 until totalButtons).random() }
        sequence.addAll(newSequence)

        delay(1000)
        
        for (index in sequence) {
            buttonColors[index] = flashColors[index]
            delay(500)
            buttonColors[index] = Color.Gray
            delay(300)
        }

        isSequencePlaying = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd9c9b6)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "niveau : ${level}",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF6B6B47),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Grille
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(3) { col ->
                            val index = row * 3 + col

                            Button(
                                onClick = {
                                    scope.launch {
                                        if (isSequencePlaying || resultat.value.isNotEmpty()) return@launch

                                        buttonColors[index] = flashColors[index]
                                        delay(300)
                                        buttonColors[index] = Color.Gray

                                        userInput.add(index)
                                        if (userInput.size == sequence.size) {
                                            if (userInput.toList() == sequence.toList()) {
                                                resultat.value = "gagné"
                                                level++
                                                resetKey++
                                            } else {
                                                resultat.value = "perdu"
                                            }
                                            userInput.clear()
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .padding(2.dp),
                                shape = RoundedCornerShape(12.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 30.dp,
                                    pressedElevation = 2.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = buttonColors[index]
                                )
                            ) {}
                        }
                    }
                }
            }
        }

        // Affichage GameOver si perdu
        if (resultat.value == "perdu") {
            Spacer(modifier = Modifier.height(24.dp))
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
