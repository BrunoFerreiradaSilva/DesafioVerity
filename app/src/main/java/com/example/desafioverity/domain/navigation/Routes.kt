package com.example.desafioverity.domain.navigation

import com.example.desafioverity.domain.helpers.INITIAL
import com.example.desafioverity.domain.helpers.USER_DETAIL

sealed class Routes(val route: String) {
    data object Users : Routes(INITIAL)
    data object Details : Routes(USER_DETAIL)
}