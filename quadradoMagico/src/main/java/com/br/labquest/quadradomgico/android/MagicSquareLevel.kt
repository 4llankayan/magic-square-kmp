package com.br.labquest.quadradomgico.android

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.labquest.quadradomgico.MagicSquare


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelSquare(navController: NavController, square: Array<IntArray>, level: Int, constantSum: Int) {
    var result by remember { mutableStateOf<MagicSquare.Companion.MagicSquareResult?>(null) }
    var clickCount by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nível ${level.toString()}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("A soma constante deste nível é: ${constantSum.toString()}")

                Spacer(modifier = Modifier.height(16.dp))

                MagicSquare(square = square)

                Spacer(modifier = Modifier.height(16.dp))

                CenteredButton(
                    onClick = {
                        result = MagicSquare.isMagicSquare(square)
                        clickCount++
                    },
                    text = "Verificar"
                )

                Spacer(modifier = Modifier.height(16.dp))

                result?.let {
                    LaunchedEffect(clickCount) {
                        val message = when {
                            it.hasDuplicates -> "Há números repetidos no quadrado."
                            it.isMagic -> "É um Quadrado Mágico com soma constante ${it.magicSum}!"
                            else -> "Não é um Quadrado Mágico."
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}