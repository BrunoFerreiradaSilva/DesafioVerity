package com.example.desafioverity.presentation.users

import com.example.desafioverity.data.model.User
import com.example.desafioverity.data.repository.users.UsersRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val usersRepository: UsersRepository) {

    operator fun invoke(page:Int):Flow<DataState<List<User>>>{
        return usersRepository.getAllUsers(page)
    }
}