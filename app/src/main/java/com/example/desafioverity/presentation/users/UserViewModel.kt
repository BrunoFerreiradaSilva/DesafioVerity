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
    val search: List<User>
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
                        _uiState.value = _uiState.value.copy(users = state.data)
                    }
                    is DataState.Error -> {}
                    is DataState.Loading -> {}
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
                                _uiState.value.copy(search = state.data, users = currentUsers)
                        }
                        is DataState.Error -> {}
                        is DataState.Loading -> {}
                    }
                }
            }
        }

    }
}