package com.example.desafioverity.data.service

import com.example.desafioverity.data.response.UsersDto
import retrofit2.http.GET

interface Service {

    @GET("/users")
    suspend fun getAllUsers(): List<UsersDto>
}