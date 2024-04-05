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
    val users: List<UserEntity>,
    val page: Int = 1
)

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: UserUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UserUiData> =
        MutableStateFlow(UserUiData(users = emptyList()))

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            useCase.invoke(_uiState.value.page).collect(::getAllUsers)
        }
    }

    private fun getAllUsers(state: DataState<List<UserEntity>>){
        when(state){
            is DataState.Data -> {
                _uiState.value = _uiState.value.copy(users = state.data)
            }
            is DataState.Error -> {}
            is DataState.Loading -> {}
        }
    }

    fun getMoreUsers(){
        viewModelScope.launch {
            val updateList = mutableListOf<UserEntity>()
            val currentList = _uiState.value.users
            val nexPage = _uiState.value.page + 1

            useCase.invoke(nexPage).collect { state ->
                when(state){
                    is DataState.Data -> {
                        updateList.addAll(currentList)
                        updateList.addAll(state.data)
                        _uiState.value = _uiState.value.copy(users = updateList, page = nexPage)
                    }
                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                }
            }
        }
    }
}