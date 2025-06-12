package com.example.memory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.memory.ui.theme.MemoryTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFa17af0)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.memoire), // logo.png dans res/drawable/
                contentDescription = "Logo de l'application",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bienvenu",
                color = Color(0xFFFFFFFF),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    /* rien pour le moment */
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFa17af0)
                ))
            {
                Text("commencer")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoryTheme {
        Greeting()
    }
}
