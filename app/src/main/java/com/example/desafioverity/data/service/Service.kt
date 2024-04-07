package com.example.desafioverity.data.service

import com.example.desafioverity.data.response.RepoDTO
import com.example.desafioverity.data.response.SearchDTO
import com.example.desafioverity.data.response.UserDetailDTO
import com.example.desafioverity.data.response.UserDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("/users")
    suspend fun getAllUsers(
        @Query("per_page") page:Int = 100
    ): List<UserDTO>

    @GET("/users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String,
    ): UserDetailDTO

    @GET("/users/{username}/repos")
    suspend fun getAllRepos(
        @Path("username") userName: String
    ): List<RepoDTO>

    @GET("/search/users")
    suspend fun getUser(
        @Query("q") name:String,
        @Query("per_page") page: Int = 100
    ): SearchDTO
}