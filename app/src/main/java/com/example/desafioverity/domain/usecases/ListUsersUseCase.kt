package com.example.desafioverity.domain.usecases

import com.example.desafioverity.domain.model.User
import com.example.desafioverity.data.repository.users.UsersRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListUsersUseCase @Inject constructor(private val usersRepository: UsersRepository) {

    operator fun invoke(): Flow<DataState<List<User>>> {
        return usersRepository.getAllUsers()
    }
}