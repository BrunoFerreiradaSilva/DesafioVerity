package com.example.desafioverity.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.desafioverity.presentation.component.UserLazyColumComponent
import com.example.desafioverity.presentation.stateScreen.error.ErrorScreenState
import com.example.desafioverity.presentation.stateScreen.loading.SearchLoading
import com.example.desafioverity.presentation.stateScreen.loading.UsersLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Users(
    state: UserUiData,
    modifier: Modifier,
    navigateToDetails: (String) -> Unit,
    searchUser: (String) -> Unit,
    tryAgain: () -> Unit
) {
    if (state.isLoading) {
        UsersLoading(modifier = modifier)
    }

    if (state.isError) {
        ErrorScreenState(modifier = modifier) {
            tryAgain()
        }
    }

    if (state.isData) {
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        Column {
            SearchBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = ShapeDefaults.Small,
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    searchUser(it)
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                    searchUser("")
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear icon"
                        )
                    }
                }
            ) {
                if (state.isSearchLoading) {
                    SearchLoading(modifier = modifier)
                }

                if (state.isSearchError) {
                    ErrorScreenState(modifier = modifier) {
                        tryAgain()
                    }
                }

                if (state.isSearchData) {
                    UserLazyColumComponent(users = state.search, modifier = modifier) {
                        navigateToDetails(it)
                    }
                }
            }
            UserLazyColumComponent(users = state.users, modifier = modifier) {
                navigateToDetails(it)
            }
        }

    }
}