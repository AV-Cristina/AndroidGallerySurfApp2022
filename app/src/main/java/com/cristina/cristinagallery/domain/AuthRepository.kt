package com.cristina.cristinagallery.domain

import com.cristina.cristinagallery.domain.model.User
import com.cristina.cristinagallery.utils.Request
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun auth(login: String, password: String): Flow<Request<User>>
}