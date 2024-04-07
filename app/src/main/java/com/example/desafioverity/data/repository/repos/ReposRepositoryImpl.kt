package com.example.desafioverity.data.repository.repos

import com.example.desafioverity.data.model.Repos
import com.example.desafioverity.data.service.Service
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.domain.helpers.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(private val service: Service) : ReposRepository {
    override fun getAllRepos(name: String): Flow<DataState<List<Repos>>> = flow {
        emit(DataState.Loading(LoadingState.Loading))
        try {
            val result = service.getAllRepos(name).map { repo ->
                Repos(
                    id = repo.id,
                    nodeId = repo.nodeId,
                    name = repo.name,
                    fullName = repo.fullName,
                    private = repo.private,
                    owner = repo.owner,
                    avatarUrl = repo.avatarUrl,
                    description = repo.description,
                    size = repo.size,
                    language = repo.language,
                    forks = repo.forks,
                    watchers = repo.watchers,
                    visibility = repo.visibility,
                    updatedAt = repo.updatedAt,
                    stargazersCount = repo.stargazersCount,
                    watchersCount = repo.watchersCount
                )
            }
            emit(DataState.Data(data = result))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }
}