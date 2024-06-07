package com.br.labquest.quadradomgico.android

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(onNavigateToFreeMode: () -> Unit, onNavigateToStoryMode: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Quadrado Mágico",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = onNavigateToFreeMode,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Modo Livre")
            }
            Button(
                onClick = onNavigateToStoryMode,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Modo História")
            }
        }
    }
}