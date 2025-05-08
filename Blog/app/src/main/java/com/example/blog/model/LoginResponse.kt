package com.example.newsapp.model

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val data: UserData?
)
