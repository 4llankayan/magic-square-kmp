package com.br.labquest.quadradomgico.android

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun MagicSquare(square: Array<IntArray>) {
    Column {
        for (row in square.indices) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for (col in square[row].indices) {
                    val cellValue = remember { mutableStateOf(square[row][col].takeIf { it != 0 }?.toString() ?: "") }
                    val isDuplicate = remember { mutableStateOf(false) }

                    if (cellValue.value.isNotBlank()) {
                        val value = cellValue.value.toInt()
                        val flatSquare = square.flatMap { it.toList() }
                        isDuplicate.value = flatSquare.count { it == value } > 1
                    }

                    BasicTextField(
                        value = cellValue.value,
                        onValueChange = { newValue ->
                            cellValue.value = newValue
                            square[row][col] = newValue.toIntOrNull() ?: 0

                            if (newValue.isNotBlank()) {
                                val value = newValue.toInt()
                                val flatSquare = square.flatMap { it.toList() }
                                isDuplicate.value = flatSquare.count { it == value } > 1
                            } else {
                                isDuplicate.value = false
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .border(1.dp, if (isDuplicate.value) Color.Red else MaterialTheme.colorScheme.primary)
                            .padding(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
        }
    }
}
