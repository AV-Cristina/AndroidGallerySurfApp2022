package com.cristina.cristinagallery.data.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user_info")
    val userInfo: UserInfoObj
)