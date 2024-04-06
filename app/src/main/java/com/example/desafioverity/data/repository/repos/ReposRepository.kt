package com.example.desafioverity.data.repository.repos

import com.example.desafioverity.data.model.Repos
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun getAllRepos(name:String): Flow<DataState<List<Repos>>>
}