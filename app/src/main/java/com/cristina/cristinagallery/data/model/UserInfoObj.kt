package com.cristina.cristinagallery.data.model

import com.google.gson.annotations.SerializedName

data class UserInfoObj(
    @SerializedName("id")
    val id: String,               // Идентификатор пользователя, example: 123e4567-e89b-12d3-a456-426614174000
    @SerializedName("phone")
    val phone: String,            // Телефон пользователя
    @SerializedName("email")
    val email: String,            // Электронная почта пользователя
    @SerializedName("firstName")
    val firstName: String,        // Имя пользователя, example: Василий
    @SerializedName("lastName")
    val lastName: String,         // Фамилия пользователя, example: Пупковский
    @SerializedName("avatar")
    val avatar: String,           // Ссылка на фото пользователя, example: https://bit.ly/3mnUUhB
    @SerializedName("city")
    val city: String,             // Город пользователя
    @SerializedName("about")
    val about: String             // Описание пользователя, example: Люблю смешные картинки
)