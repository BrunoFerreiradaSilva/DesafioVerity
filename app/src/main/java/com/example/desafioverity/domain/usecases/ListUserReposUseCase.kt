package com.example.desafioverity.domain.usecases

import com.example.desafioverity.domain.model.Repos
import com.example.desafioverity.data.repository.repos.UserRepoRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListUserReposUseCase @Inject constructor(private val repository: UserRepoRepository) {
    operator fun invoke(name: String): Flow<DataState<List<Repos>>> {
        return repository.getAllRepos(name)
    }
}