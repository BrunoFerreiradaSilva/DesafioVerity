package com.example.desafioverity.presentation.details

import com.example.desafioverity.data.model.UserDetail
import com.example.desafioverity.data.repository.details.UserDetailRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(private val repository: UserDetailRepository) {

    operator fun invoke(userName: String): Flow<DataState<UserDetail>> {
        return repository.userDetail(userName)
    }
}