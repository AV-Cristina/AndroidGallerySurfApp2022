package com.cristina.cristinagallery.data

import com.cristina.cristinagallery.data.mapper.mapToDomain
import com.cristina.cristinagallery.data.model.AuthRequest
import com.cristina.cristinagallery.data.service.AuthService
import com.cristina.cristinagallery.domain.AuthRepository
import com.cristina.cristinagallery.domain.model.User
import com.cristina.cristinagallery.utils.Request
import com.cristina.cristinagallery.utils.RequestUtils.requestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
    //private val tokenStorage: TokenStorage // TODO
) : AuthRepository {

    override suspend fun auth(login: String, password: String): Flow<Request<User>> {
        return requestFlow {
            val authResponse = authService.auth(AuthRequest(login, password))
            val user = authResponse.mapToDomain()
            // tokenStorage.saveToken(user.token)
            user
        }
    }
}