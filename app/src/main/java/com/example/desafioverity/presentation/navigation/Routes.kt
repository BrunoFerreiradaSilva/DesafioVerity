package com.example.desafioverity.presentation.navigation

private const val INITIAL = "allUsers"
private const val USER_DETAIL = "userDetail"


sealed class Routes(val route: String) {
    data object Users : Routes(INITIAL)
    data object Details : Routes(USER_DETAIL)
}