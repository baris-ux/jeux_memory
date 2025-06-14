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
                text = "Vous avez : ${resultat.value}",
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
                                        if (resultat.value.isNotEmpty()) return@launch

                                        buttonColors[index] = flashColors[index]
                                        delay(300)
                                        buttonColors[index] = Color.Gray

                                        userInput.add(index)
                                        val currentIndex = userInput.lastIndex
                                        if (userInput[currentIndex] != sequence[currentIndex]) {
                                            resultat.value = "perdu"
                                        } else if (userInput.size == sequence.size) {
                                            resultat.value = "gagné"
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
