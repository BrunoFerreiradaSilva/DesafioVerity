package com.example.desafioverity.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl:String
)
