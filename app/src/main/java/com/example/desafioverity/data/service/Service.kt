package com.example.desafioverity.data.service

import com.example.desafioverity.data.response.ReposDto
import com.example.desafioverity.data.response.SearchDTO
import com.example.desafioverity.data.response.UserDetailDto
import com.example.desafioverity.data.response.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("/users")
    suspend fun getAllUsers(
        @Query("per_page") page:Int = 100
    ): List<UserDto>

    @GET("/users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String,
    ): UserDetailDto

    @GET("/users/{username}/repos")
    suspend fun getAllRepos(
        @Path("username") userName: String
    ): List<ReposDto>

    @GET("/search/users")
    suspend fun getUser(
        @Query("q") name:String,
        @Query("per_page") page: Int = 100
    ): SearchDTO
}