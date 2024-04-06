package com.example.desafioverity.data.repository.details

import com.example.desafioverity.data.model.UserDetail
import com.example.desafioverity.data.service.Service
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.domain.helpers.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(private val service: Service) :
    UserDetailRepository {
    override fun userDetail(userName: String): Flow<DataState<UserDetail>> = flow {
        emit(DataState.Loading(LoadingState.Loading))
        delay(5000)
        try {
            val result = service.getUserDetail(userName)
            val user = UserDetail(
                login = result.login,
                avatarUrl = result.avatarUrl,
                name = result.name,
                company = result.company,
                location = result.location,
                publicRepos = result.publicRepos,
                followers = result.followers,
                following = result.following
            )

            emit(DataState.Data(data = user))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }
}