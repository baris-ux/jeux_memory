package com.example.memory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.unit.dp

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            fruits.take(2).forEach { fruit ->
                Row {
                    repeat(2) {
                        Image(
                            painter = painterResource(id = fruit),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(RoundedCornerShape(22.dp))
                                .background(Color.LightGray)
                                .size(100.dp)
                                .padding(4.dp)
                                .clickable {
                                    // Action au clic : par exemple afficher un log ou un toast
                                },
                        )
                    }
                }
            }
        }
    }
}






/*Image(
painter = painterResource(id = R.drawable.apple),
contentDescription = "Image de pomme",
modifier = Modifier
.clip(RoundedCornerShape(22.dp))
.background(Color.LightGray)
.padding(16.dp)
)*/