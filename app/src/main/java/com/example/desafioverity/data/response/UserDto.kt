package com.example.desafioverity.data.response

import com.google.gson.annotations.SerializedName

data class UserDto (
    val login: String,
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatarUrl:String
)