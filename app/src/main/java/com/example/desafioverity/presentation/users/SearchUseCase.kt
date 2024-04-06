package com.example.desafioverity.presentation.users

import com.example.desafioverity.data.model.User
import com.example.desafioverity.data.repository.users.UsersRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke(name:String): Flow<DataState<List<User>>>{
        return repository.getUser(name)
    }
}