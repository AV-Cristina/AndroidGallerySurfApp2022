package com.cristina.cristinagallery.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristina.cristinagallery.data.AuthRepositoryImpl
import com.cristina.cristinagallery.utils.LoadState
import com.cristina.cristinagallery.utils.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) : ViewModel() {

    val mutableLoginError = MutableLiveData<LoginError>()
    val mutablePasswordError = MutableLiveData<PasswordError>()
    val loadState = MutableLiveData<LoadState>()

    private val inputValidator by lazy {
        InputValidator(mutableLoginError, mutablePasswordError)
    }

    private var login: String = ""
    private var password: String = ""

    fun auth() {
        if (inputValidator.isFieldValid(login, password)) {
            viewModelScope.launch(Dispatchers.IO) {
                authRepository.auth("+7$login", password).collect { requestState ->
                    when (requestState) {
                        is Request.Loading -> {
                            loadState.postValue(LoadState.LOADING)
                        }
                        is Request.Success -> {
                            loadState.postValue(LoadState.SUCCESS)
                        }
                        is Request.Error -> {
                            loadState.postValue(LoadState.ERROR)
                        }
                    }
                }
            }
        }
    }

    fun setLogin(login: String) {
        this.login = login
    }

    fun setPassword(password: String) {
        this.password = password
    }
}