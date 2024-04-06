package com.example.desafioverity.data.service

import com.example.desafioverity.data.response.ReposDto
import com.example.desafioverity.data.response.UserDetailDto
import com.example.desafioverity.data.response.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("/users")
    suspend fun getAllUsers(): List<UserDto>

    @GET("/users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String,
    ): UserDetailDto

    @GET("/users/{username}/repos")
    suspend fun getAllRepos(
        @Path("username") userName: String
    ): List<ReposDto>
}