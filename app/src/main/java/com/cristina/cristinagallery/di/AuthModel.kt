package com.cristina.cristinagallery.di

import com.cristina.cristinagallery.data.AuthRepositoryImpl
import com.cristina.cristinagallery.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModel {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun providesAuthRepository(authService: AuthService) = AuthRepositoryImpl(authService)
}