package com.example.desafioverity.presentation.users

import DialogLimitRequest
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.desafioverity.R
import com.example.desafioverity.presentation.component.UserLazyColumComponent
import com.example.desafioverity.presentation.component.ErrorScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Users(
    state: UserUiData,
    modifier: Modifier,
    navigateToDetails: (String) -> Unit,
    searchUser: (String) -> Unit,
    tryAgain: (Boolean) -> Unit
) {
    DialogLimitRequest(showDialog = state.limitRequest, dateForRequest = state.dateForNewRequest) {

    }
    if (state.isLoading) {
        UsersLoading(modifier = modifier)
    }

    if (state.isError) {
        ErrorScreenState(modifier = modifier) {
            tryAgain(true)
        }
    }

    if (state.isData) {
        var search by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        Column {
            SearchBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = ShapeDefaults.Small,
                query = search,
                onQueryChange = { search = it },
                onSearch = {
                    searchUser(it)
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text(text = stringResource(R.string.search)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = modifier.clickable {
                                if (search.isNotEmpty()) {
                                    search = ""
                                    searchUser("")
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.clear_icon)
                        )
                    }
                }
            ) {
                DialogLimitRequest(showDialog = state.limitRequest, dateForRequest = state.dateForNewRequest) {

                }
                if (state.isSearchLoading) {
                    UsersLoading(modifier = modifier)
                }

                if (state.isError) {
                    ErrorScreenState(modifier = modifier) {
                        tryAgain(false)
                    }
                }

                if (state.isSearchError){
                    SearchNotFoundState(modifier = modifier)
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