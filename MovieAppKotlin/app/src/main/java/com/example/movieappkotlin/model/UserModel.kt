package com.example.movieappkotlin.model

data class UserModel(
    val userId: Int,
    val personName: String,
    val userEmail: String,
    val userAddress: String,
    val userDob: String,
    val userPassword: String
)