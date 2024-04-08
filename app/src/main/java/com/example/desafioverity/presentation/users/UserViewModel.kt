package com.example.desafioverity.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioverity.data.repository.preferences.UserPreferenceRepository
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.domain.model.User
import com.example.desafioverity.domain.usecases.ListUsersUseCase
import com.example.desafioverity.domain.usecases.SearchUserByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

const val RELOAD_SEARCH = "reloadSearch"

data class UserUiData(
    val users: List<User>,
    val search: List<User>,
    val isData: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSearchData: Boolean = false,
    val isSearchLoading: Boolean = false,
    val isSearchError: Boolean = false,
    val limitRequest: Boolean = false,
    val dateForNewRequest: String = ""
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val listUsersUseCase: ListUsersUseCase,
    private val searchUserByNameUseCase: SearchUserByNameUseCase,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserUiData> =
        MutableStateFlow(UserUiData(users = emptyList(), search = emptyList()))
    val uiState = _uiState.asStateFlow()
    private val currentUsers: MutableList<User> = mutableListOf()

    init {
        getAllUsers()
    }

    fun getAllUsers() {
        viewModelScope.launch {
            listUsersUseCase.invoke().collect(::handleUserResult)
        }
    }

    private fun handleUserResult(state: DataState<List<User>>) {
        when (state) {
            is DataState.Data -> {
                currentUsers.addAll(state.data)
                _uiState.value = _uiState.value.copy(
                    users = state.data,
                    isData = true,
                    isLoading = false,
                    isError = false,
                    limitRequest = false
                )
            }

            is DataState.Error -> {
                _uiState.value =
                    _uiState.value.copy(
                        isError = true,
                        isLoading = false,
                        isData = false,
                        limitRequest = false
                    )
            }

            is DataState.Loading -> {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = true,
                        isError = false,
                        isData = false,
                        limitRequest = false
                    )
            }

            is DataState.Limit -> {
                viewModelScope.launch {
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


    fun searchUser(userName: String) {
        if (userName == RELOAD_SEARCH) {
            reloadSearch()
        }
        if (userName.isEmpty()) {
            clearSearch()
        } else {
            viewModelScope.launch {
                searchUserByNameUseCase.invoke(userName).collect { state ->
                    when (state) {
                        is DataState.Data -> {
                            val emptySearch = if (state.data.isEmpty()) true else false
                            _uiState.value =
                                _uiState.value.copy(
                                    search = state.data,
                                    users = currentUsers,
                                    isSearchData = true,
                                    isSearchError = emptySearch,
                                    isSearchLoading = false,
                                    isError = false
                                )
                        }

                        is DataState.Error -> {
                            searchError()
                        }

                        is DataState.Loading -> {
                            searchLoading()
                        }

                        is DataState.Limit -> {
                            limitRequest()
                        }
                    }
                }
            }
        }

    }

    private fun limitRequest() {
        viewModelScope.launch {
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

    private fun searchLoading() {
        _uiState.value = _uiState.value.copy(
            isError = false,
            isSearchLoading = true,
            isSearchError = false,
            isSearchData = false
        )
    }

    private fun searchError() {
        _uiState.value = _uiState.value.copy(
            isError = true,
            isSearchLoading = false,
            isSearchData = false
        )
    }

    private fun reloadSearch() {
        _uiState.value = _uiState.value.copy(
            isError = false,
            isSearchLoading = true,
            isSearchError = false,
            isSearchData = false
        )
    }

    private fun clearSearch() {
        _uiState.value = _uiState.value.copy(
            search = emptyList(),
            users = currentUsers,
            isSearchLoading = false,
            isSearchData = false,
            isSearchError = false,
            isError = false
        )
    }
}