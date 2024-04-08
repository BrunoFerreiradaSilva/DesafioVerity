package com.example.desafioverity.data.response

import com.google.gson.annotations.SerializedName

data class UserDetailDTO(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int
)
