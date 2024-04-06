package com.example.desafioverity.presentation.details

import com.example.desafioverity.data.model.Repos
import com.example.desafioverity.data.repository.repos.ReposRepository
import com.example.desafioverity.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoUseCase @Inject constructor(private val repository: ReposRepository) {
    operator fun invoke(name: String): Flow<DataState<List<Repos>>> {
        return repository.getAllRepos(name)
    }
}