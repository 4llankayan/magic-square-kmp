package com.br.labquest.quadradomgico.android

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.labquest.quadradomgico.MagicSquare

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeMode(navController: NavController) {
    var size by remember { mutableIntStateOf(3) }
    var square by remember { mutableStateOf(Array(size) { IntArray(size) }) }
    var result by remember { mutableStateOf<MagicSquare.Companion.MagicSquareResult?>(null) }
    var clickCount by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modo Livre") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        size++
                        val newSquare = Array(size) { IntArray(size) }
                        for (i in square.indices) {
                            for (j in square[i].indices) {
                                if (i < size && j < size) {
                                    newSquare[i][j] = square[i][j]
                                }
                            }
                        }
                        square = newSquare
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "Increase Size")
                    }
                }
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

@Composable
fun CenteredButton(onClick: () -> Unit, text: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onClick) {
            Text(text)
        }
    }
}