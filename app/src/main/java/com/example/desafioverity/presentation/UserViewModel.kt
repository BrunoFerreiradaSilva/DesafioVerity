package com.example.desafioverity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioverity.data.entity.UserEntity
import com.example.desafioverity.domain.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserUiData(
    val users: List<UserEntity>
)

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UserUiData> =
        MutableStateFlow(UserUiData(users = emptyList()))

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userUseCase.invoke().collect(::getAllUsers)
        }
    }

    private fun getAllUsers(state: DataState<List<UserEntity>>){
        when(state){
            is DataState.Data -> {
                _uiState.value = _uiState.value.copy(state.data)
            }
            is DataState.Error -> {}
            is DataState.Loading -> {}
        }
    }
}