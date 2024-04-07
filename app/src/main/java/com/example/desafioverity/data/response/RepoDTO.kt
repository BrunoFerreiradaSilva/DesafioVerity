package com.example.desafioverity.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RepoDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("owner")
    val owner: UserDTO,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("watchers")
    val watchers: Int,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("updated_at")
    val updatedAt: Date,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int
)