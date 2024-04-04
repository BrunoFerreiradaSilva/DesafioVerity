package com.example.desafioverity.data.repository

import com.example.desafioverity.data.entity.UserEntity
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getAllUsers(): Flow<DataState<List<UserEntity>>>
}