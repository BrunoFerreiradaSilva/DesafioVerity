package com.example.desafioverity.data.service

import com.example.desafioverity.data.response.UsersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("/users")
    suspend fun getAllUsers(
        @Query("per_page") page: Int,
    ): List<UsersDto>
}