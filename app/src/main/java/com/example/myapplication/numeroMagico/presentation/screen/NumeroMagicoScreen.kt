package com.example.myapplication.numeroMagico.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.numeroMagico.presentation.viewmodels.NumeroMagicoViewModel


@Composable
fun NumeroMagicoScreen(
    viewModel: NumeroMagicoViewModel = viewModel()
) {
    val message by viewModel.message.collectAsState()
    val lives by viewModel.lives.collectAsState()
    val userGuess by viewModel.userGuess.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = message, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = userGuess,
            onValueChange = { viewModel.onChangeGuess(it) },
            label = { Text("Ingresa un número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.checkGuess() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Probar número")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { viewModel.resetGame() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar juego")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Vidas restantes: $lives")
    }
}
@Preview(showBackground = true)
@Composable
fun NumeroMagicoScreenPreview() {
    MaterialTheme {
        NumeroMagicoScreen()
    }
}
