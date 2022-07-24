package com.cristina.cristinagallery.data.mapper

import com.cristina.cristinagallery.data.model.AuthResponse
import com.cristina.cristinagallery.domain.model.User
import com.cristina.cristinagallery.domain.model.UserInfo

/**
 * Маппер объектов полученных от сервера в объекты, с которыми удобнее работать в коде
 */
fun AuthResponse.mapToDomain(): User {
    return User(
        this.token,
        UserInfo(
            this.userInfo.id,
            this.userInfo.phone,
            this.userInfo.email,
            this.userInfo.firstName,
            this.userInfo.lastName,
            this.userInfo.avatar,
            this.userInfo.city,
            this.userInfo.about
        )
    )
}