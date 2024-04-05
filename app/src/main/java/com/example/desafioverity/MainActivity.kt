package com.example.desafioverity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.desafioverity.domain.navigation.Routes
import com.example.desafioverity.presentation.UserViewModel
import com.example.desafioverity.presentation.users.Users
import com.example.desafioverity.ui.theme.DesafioVerityTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

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
                    NavGraph(navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.Users.route) {
        composable(
            route = Routes.Users.route
        ) {
            val viewModel = hiltViewModel<UserViewModel>()
            val state = viewModel.uiState.collectAsState()
            Users(state = state.value.users, Modifier, navigateToDetails = {}) {
                viewModel.getMoreUsers()
            }
        }
    }
}
