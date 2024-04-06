package com.example.desafioverity.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioverity.data.model.User
import com.example.desafioverity.domain.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserUiData(
    val users: List<User>,
    val search: List<User>,
    val isData: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSearchData:Boolean= false,
    val isSearchLoading:Boolean= false,
    val isSearchError:Boolean= false
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserUiData> =
        MutableStateFlow(UserUiData(users = emptyList(), search = emptyList()))
    val uiState = _uiState.asStateFlow()
    private val currentUsers: MutableList<User> = mutableListOf()

    fun getAllUsers() {
        viewModelScope.launch {
            userUseCase.invoke().collect { state ->
                when (state) {
                    is DataState.Data -> {
                        currentUsers.addAll(state.data)
                        _uiState.value = _uiState.value.copy(
                            users = state.data,
                            isData = true,
                            isLoading = false,
                            isError = false
                        )
                    }

                    is DataState.Error -> {
                        _uiState.value =
                            _uiState.value.copy(isError = true, isLoading = false, isData = false)
                    }

                    is DataState.Loading -> {
                        _uiState.value =
                            _uiState.value.copy(isLoading = true, isError = false, isData = false)
                    }
                }
            }
        }
    }

    fun searchUser(userName: String) {
        if (userName.isEmpty()) {
            _uiState.value = _uiState.value.copy(search = emptyList(), users = currentUsers)
        } else {
            viewModelScope.launch {
                searchUseCase.invoke(userName).collect { state ->
                    when (state) {
                        is DataState.Data -> {
                            _uiState.value =
                                _uiState.value.copy(search = state.data, users = currentUsers, isSearchData = true, isSearchError = false, isSearchLoading = false)
                        }

                        is DataState.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isSearchError = true,
                                isSearchLoading = false,
                                isSearchData = false
                            )
                        }

                        is DataState.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isSearchLoading = true,
                                isSearchError = false,
                                isSearchData = false
                            )
                        }
                    }
                }
            }
        }

    }
}