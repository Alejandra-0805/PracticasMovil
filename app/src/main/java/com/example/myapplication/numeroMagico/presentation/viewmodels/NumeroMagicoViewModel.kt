package com.example.myapplication.numeroMagico.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NumeroMagicoViewModel : ViewModel() {

    private var x = (1..100).random()

    private val _message = MutableStateFlow("Adivina el número mágico (1-100)")
    val message: StateFlow<String> = _message

    private val _lives = MutableStateFlow(5)
    val lives: StateFlow<Int> = _lives

    private val _userGuess = MutableStateFlow("")
    val userGuess: StateFlow<String> = _userGuess

    fun onChangeGuess(newGuess: String) {
        _userGuess.value = newGuess
    }

    fun checkGuess() {
        if (_lives.value <= 0) {
            _message.value = "Te quedaste sin vidas. El número era $x"
            return
        }

        val guess = _userGuess.value.toIntOrNull()
        if (guess == null) {
            _message.value = "Ingresa un número válido"
            return
        }

        if (guess !in 1..100) {
            _message.value = "El número debe estar entre 1 y 100"
            return
        }

        when {
            guess == x -> {
                _message.value = "¡Adivinaste el número mágico!"
            }

            guess < x -> {
                _lives.value--
                _message.value =
                    if (x - guess <= 5)
                        "Estás cerca, pero es un poco más alto"
                    else
                        "⬆ Muy bajo, intenta un número mayor"
            }

            guess > x -> {
                _lives.value--
                _message.value =
                    if (guess - x <= 5)
                        "Estás cerca, pero es un poco más bajo"
                    else
                        "Muy alto, intenta un número menor"
            }
        }
    }

    fun resetGame() {
        x = (1..100).random()
        _lives.value = 5
        _userGuess.value = ""
        _message.value = "Nuevo juego: Adivina el número mágico (1-100)"
    }
}
