package com.example.desafioverity.data.response

import com.google.gson.annotations.SerializedName

data class UsersDto (
    val login: Int,
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
)