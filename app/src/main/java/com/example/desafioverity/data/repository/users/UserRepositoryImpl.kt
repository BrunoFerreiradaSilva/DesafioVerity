package com.example.desafioverity.data.repository.users

import com.example.desafioverity.data.model.User
import com.example.desafioverity.data.service.Service
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.domain.helpers.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val service: Service) : UsersRepository {
    override fun getAllUsers(): Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        delay(5000)
        try {
            val users = service.getAllUsers().map {
                User(
                    login = it.login,
                    id = it.id,
                    nodeId = it.nodeId,
                    avatarUrl = it.avatarUrl
                )
            }
            emit(DataState.Data(data = users))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }

    override fun getUser(name: String): Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        try {
            val result = service.getUser(name).items.map {userDto -> User(
                id = userDto.id,
                login = userDto.login,
                nodeId = userDto.nodeId,
                avatarUrl = userDto.avatarUrl
            )}


            emit(DataState.Data(data = result))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }
}