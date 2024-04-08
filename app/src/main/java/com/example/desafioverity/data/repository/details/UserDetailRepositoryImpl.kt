package com.example.desafioverity.data.repository.details

import com.example.desafioverity.data.repository.preferences.UserPreferenceRepository
import com.example.desafioverity.domain.model.UserDetail
import com.example.desafioverity.data.service.Service
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.domain.helpers.LimitState
import com.example.desafioverity.domain.helpers.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(
    private val service: Service,
    private val userPreferenceRepository: UserPreferenceRepository
) :
    UserDetailRepository {
    override fun userDetail(userName: String): Flow<DataState<UserDetail>> = flow {
        emit(DataState.Loading(LoadingState.Loading))
        try {
            val result = service.getUserDetail(userName)
            val user = UserDetail(
                login = result.login,
                avatarUrl = result.avatarUrl?: "",
                name = result.name?: result.login,
                company = result.company?: "",
                location = result.location?: "",
                publicRepos = result.publicRepos,
                followers = result.followers,
                following = result.following
            )

            emit(DataState.Data(data = user))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
        userPreferenceRepository.getRemaining.collect {
            if (it == "0"){
                emit(DataState.Limit(LimitState.Limit))
            }
        }
    }
}