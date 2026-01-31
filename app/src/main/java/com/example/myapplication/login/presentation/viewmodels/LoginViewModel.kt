package com.example.myapplication.login.presentation.viewmodels

import android.R
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.logging.Logger

class LoginViewModel(
    private val LoginUseCase : LoginUseCase) : ViewModel (){

    private val _username = MutableStateFlow(value = "")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow(value = "")
    val password = _password.asStateFlow()

    private val _error = MutableStateFlow(value = "")
    val error = _error.asStateFlow()

    fun onChangeUsername(it : String){
        _username.value = it
    }
    fun onChangePassword(it : String){
        _password.value = it
    }


}