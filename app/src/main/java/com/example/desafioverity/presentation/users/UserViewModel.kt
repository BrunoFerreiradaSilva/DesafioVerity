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
    val users: List<User>
)

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: UserUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UserUiData> =
        MutableStateFlow(UserUiData(users = emptyList()))
    val uiState = _uiState.asStateFlow()

     fun getAllUsers() {
        viewModelScope.launch {
            useCase.invoke().collect{state ->
                when (state) {
                    is DataState.Data -> {
                        _uiState.value = _uiState.value.copy(users = state.data)
                    }
                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                }
            }
        }

    }
}