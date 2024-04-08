package com.example.desafioverity.domain.usecases

import com.example.desafioverity.domain.model.UserDetail
import com.example.desafioverity.data.repository.details.UserDetailRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(private val repository: UserDetailRepository) {

    operator fun invoke(userName: String): Flow<DataState<UserDetail>> {
        return repository.userDetail(userName)
    }
}