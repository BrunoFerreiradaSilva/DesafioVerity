package com.example.desafioverity.data.repository.users

import com.example.desafioverity.data.model.User
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getAllUsers(page:Int): Flow<DataState<List<User>>>
}