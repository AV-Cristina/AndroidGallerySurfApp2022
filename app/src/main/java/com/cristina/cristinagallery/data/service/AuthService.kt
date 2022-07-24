package com.cristina.cristinagallery.data.service

import com.cristina.cristinagallery.data.model.AuthRequest
import com.cristina.cristinagallery.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    // Авторизация пользователя по логину и паролю
    @POST("/auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): AuthResponse
}