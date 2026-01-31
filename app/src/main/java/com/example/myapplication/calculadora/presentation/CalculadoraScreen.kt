package com.example.myapplication.calculadora.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculadoraScreen() {

    var display by remember { mutableStateOf("0") }
    var numero1 by remember { mutableStateOf(0.0) }
    var operador by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.DarkGray),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = display,
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(3f)
                .padding(8.dp)
        ) {

            filaBotones(listOf("7", "8", "9", "C")) { valor ->
                when (valor) {
                    "+", "-", "×", "÷" -> {
                        numero1 = display.toDouble()
                        operador = valor
                        display = "0"
                    }
                    "=" -> {
                        val numero2 = display.toDouble()
                        val resultado = when (operador) {
                            "+" -> numero1 + numero2
                            "-" -> numero1 - numero2
                            "×" -> numero1 * numero2
                            "÷" -> if (numero2 != 0.0) numero1 / numero2 else 0.0
                            else -> numero2
                        }
                        display = resultado.toString()
                    }
                    else -> {
                        display =
                            if (display == "0") valor
                            else display + valor
                    }
                }
            }

            filaBotones(listOf("4", "5", "6", "-")) {  }
            filaBotones(listOf("1", "2", "3", "+")) {  }
            filaBotones(listOf("0", "×", "÷", "=")) {  }
        }
    }
}

@Composable
fun filaBotones(
    textos: List<String>,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        textos.forEach { texto ->
            Button(
                onClick = { onClick(texto) },
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
                    .padding(4.dp)
            ) {
                Text(texto, fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraScreen()
}
