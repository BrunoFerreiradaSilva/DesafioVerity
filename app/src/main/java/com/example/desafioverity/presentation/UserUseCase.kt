package com.example.desafioverity.presentation

import com.example.desafioverity.data.entity.UserEntity
import com.example.desafioverity.data.repository.UsersRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val usersRepository: UsersRepository) {

    operator fun invoke(page:Int):Flow<DataState<List<UserEntity>>>{
        return usersRepository.getAllUsers(page)
    }
}