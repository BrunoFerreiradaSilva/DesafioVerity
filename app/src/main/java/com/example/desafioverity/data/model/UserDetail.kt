package com.example.desafioverity.data.model

data class UserDetail(
    val login: String,
    val avatarUrl: String,
    val name: String,
    val company: String,
    val location: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)