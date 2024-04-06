package com.example.desafioverity.data.repository.details

import com.example.desafioverity.data.model.User
import com.example.desafioverity.data.model.UserDetail
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    fun userDetail(userName:String) :Flow<DataState<UserDetail>>
}