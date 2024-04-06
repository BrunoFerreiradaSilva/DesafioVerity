package com.example.desafioverity.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.desafioverity.data.model.User
import com.example.desafioverity.presentation.component.UserLazyColumComponent
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Users(
    state: List<User>,
    search: List<User>,
    modifier: Modifier,
    navigateToDetails: (String) -> Unit,
    searchUser: (String) -> Unit
) {
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
            UserLazyColumComponent(users = search, modifier = modifier) {
                navigateToDetails(it)
            }
//            val newUsers =
//                if (text.isNotBlank()) state.filter { it.login.contains(text) } else emptyList()
//            if (newUsers.isEmpty() && text.isNotBlank()) {
//                Icon(
//                    imageVector = Icons.Default.Clear,
//                    contentDescription = "empty list",
//                    modifier
//                        .fillMaxWidth()
//                        .padding(top = 50.dp)
//                        .size(50.dp)
//                )
//                Text(
//                    text = "Nenhum resultado encontrado.",
//                    textAlign = TextAlign.Center,
//                    modifier = modifier.fillMaxWidth()
//                )
//            } else if (newUsers.isEmpty()) {
//                Icon(
//                    imageVector = Icons.Default.Info,
//                    contentDescription = "empty list",
//                    modifier
//                        .fillMaxWidth()
//                        .padding(top = 50.dp)
//                        .size(50.dp)
//                )
//                Text(
//                    text = "Sua busca aparecerá aqui.",
//                    textAlign = TextAlign.Center,
//                    modifier = modifier.fillMaxWidth()
//                )
//            } else {
//
//
//            }

        }

        UserLazyColumComponent(users = state, modifier = modifier) {
            navigateToDetails(it)
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewUsers() {
    val users = User(
        login = "Brunoooo",
        id = 222,
        nodeId = "UHASUHAUAHS",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
    )
    val userUiData = UserUiData(
        users = listOf(users),
        search = listOf(users)
    )

    Users(
        state = userUiData.users,
        search = userUiData.users,
        Modifier,
        navigateToDetails = {},
        searchUser = {})
}