package com.example.desafioverity.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioverity.data.model.User
import com.example.desafioverity.data.model.UserDetail
import com.example.desafioverity.domain.helpers.DataState
import com.example.desafioverity.presentation.users.UserUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserDetailUiData(
    val user: UserDetail? = null
)

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val useCase: UserDetailUseCase) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UserDetailUiData> =
        MutableStateFlow(UserDetailUiData())
    val uiState = _uiState.asStateFlow()


    fun getDetailUser(name: String) {
        viewModelScope.launch {
            useCase.invoke(name).collect { state ->
                when (state){
                    is DataState.Data -> {
                        _uiState.value = _uiState.value.copy(user = state.data)
                    }
                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                }
            }
        }
    }
}