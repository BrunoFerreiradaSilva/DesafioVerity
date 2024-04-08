package com.example.desafioverity.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioverity.data.repository.preferences.UserPreferenceRepository
import com.example.desafioverity.domain.model.Repos
import com.example.desafioverity.domain.model.UserDetail
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.domain.usecases.ListUserReposUseCase
import com.example.desafioverity.domain.usecases.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

data class UserDetailUiData(
    val user: UserDetail? = null,
    val repos: List<Repos>,
    val isLoading: Boolean = true,
    val isData: Boolean = false,
    val isError: Boolean = false,
    val limitRequest: Boolean = false,
    val dateForNewRequest: String = ""
)

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val listUserReposUseCase: ListUserReposUseCase,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserDetailUiData> =
        MutableStateFlow(UserDetailUiData(repos = emptyList()))
    val uiState = _uiState.asStateFlow()

    fun getDetailUser(name: String) {
        viewModelScope.launch {
            getUserDetailsUseCase.invoke(name).collect { state ->
                when (state) {
                    is DataState.Data -> {
                        _uiState.value = _uiState.value.copy(
                            user = state.data,
                            isData = true,
                            isLoading = false,
                            isError = false
                        )
                    }

                    is DataState.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isError = true,
                            isLoading = false,
                            isData = false,
                            limitRequest = false
                        )
                    }

                    is DataState.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            isError = false,
                            isData = false,
                            limitRequest = false
                        )
                    }

                    is DataState.Limit -> {
                        userPreferenceRepository.getTimeForNewRequest.collect {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true,
                                isError = false,
                                isData = false,
                                limitRequest = true,
                                dateForNewRequest = it
                            )
                        }
                    }
                }
            }
        }
    }

    fun getAllRepos(name: String) {
        viewModelScope.launch {
            listUserReposUseCase.invoke(name).collect { state ->
                when (state) {
                    is DataState.Data -> {
                        _uiState.value = _uiState.value.copy(
                            repos = state.data,
                            isData = true,
                            isError = false,
                            isLoading = false,
                            limitRequest = false
                        )
                    }

                    is DataState.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isData = false,
                            isError = true,
                            isLoading = false,
                            limitRequest = false
                        )

                    }

                    is DataState.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isData = false,
                            isError = false,
                            isLoading = true,
                            limitRequest = false
                        )
                    }

                    is DataState.Limit -> {
                        userPreferenceRepository.getTimeForNewRequest.collect {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true,
                                isError = false,
                                isData = false,
                                limitRequest = true,
                                dateForNewRequest = it
                            )
                        }
                    }
                }
            }
        }
    }
}