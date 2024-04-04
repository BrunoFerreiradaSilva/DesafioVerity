package com.example.desafioverity.data.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    val login: Int,
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
)
