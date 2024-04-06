package com.example.desafioverity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.desafioverity.domain.navigation.Routes
import com.example.desafioverity.presentation.details.UserDetail
import com.example.desafioverity.presentation.details.UserDetailViewModel
import com.example.desafioverity.presentation.users.UserViewModel
import com.example.desafioverity.presentation.users.Users
import com.example.desafioverity.ui.theme.DesafioVerityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            DesafioVerityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navHostController)
                }
            }
        }
    }

    @Composable
    private fun Navigation(navHostController: NavHostController) {
        NavHost(navController = navHostController, startDestination = Routes.Users.route) {
            composable(
                route = Routes.Users.route
            ) {
                val viewModel = hiltViewModel<UserViewModel>()
                val state = viewModel.uiState.collectAsState()
                LaunchedEffect(key1 = state) {
                    viewModel.getAllUsers()
                }

                Users(state = state.value, Modifier, navigateToDetails = { nameUser ->
                    navHostController.navigate("${Routes.Details.route}/torvalds") {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }, searchUser = {
                    viewModel.searchUser(it)
                }, tryAgain = {
                    viewModel.getAllUsers()
                })
            }
            composable(
                route = "${Routes.Details.route}/{nameUser}"
            ) {
                val nameUser = it.arguments?.getString("nameUser")
                val viewModel = hiltViewModel<UserDetailViewModel>()
                val state = viewModel.uiState.collectAsState()
                LaunchedEffect(key1 = state) {
                    nameUser?.let { user ->
                        viewModel.getDetailUser(user)
                        viewModel.getAllRepos(user)
                    }
                }

                UserDetail(
                    state = state.value,
                    modifier = Modifier
                ) {
                    nameUser?.let { user ->
                        viewModel.getDetailUser(user)
                        viewModel.getAllRepos(user)
                    }
                }
            }
        }
    }
}

