package com.example.desafioverity.domain.model

import com.example.desafioverity.data.response.UserDTO
import java.util.Date

data class Repos(
    val id: Int,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: UserDTO,
    val avatarUrl: String?,
    val description: String?,
    val size: Int,
    val language: String?,
    val forks: Int,
    val watchers: Int,
    val visibility: String,
    val updatedAt: Date,
    val stargazersCount: Int,
    val watchersCount: Int
)