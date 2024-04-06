package com.example.desafioverity.data.response

import com.google.gson.annotations.SerializedName

data class UserDetailDto(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name:String,
    val company:String,
    val location:String,
    @SerializedName("public_repos")
    val publicRepos:Int,
    val followers:Int,
    val following:Int
    )
