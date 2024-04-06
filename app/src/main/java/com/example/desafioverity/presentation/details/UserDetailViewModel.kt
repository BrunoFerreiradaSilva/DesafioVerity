package com.example.desafioverity.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioverity.data.model.Repos
import com.example.desafioverity.data.model.UserDetail
import com.example.desafioverity.domain.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserDetailUiData(
    val user: UserDetail? = null,
    val repos: List<Repos>
)

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userDetailUseCase: UserDetailUseCase,
    private val repoUseCase: RepoUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserDetailUiData> =
        MutableStateFlow(UserDetailUiData(repos = emptyList()))
    val uiState = _uiState.asStateFlow()

    fun getDetailUser(name: String) {
        viewModelScope.launch {
            userDetailUseCase.invoke(name).collect { state ->
                when (state) {
                    is DataState.Data -> {
                        _uiState.value = _uiState.value.copy(user = state.data)
                    }

                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                }
            }
        }
    }

    fun getAllRepos(name: String) {
        viewModelScope.launch {
            repoUseCase.invoke(name).collect { state ->
                when (state) {
                    is DataState.Data -> {
                        _uiState.value = _uiState.value.copy(repos = state.data)
                    }

                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                }
            }
        }
    }
}