package com.example.desafioverity.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.desafioverity.data.entity.UserEntity
import com.example.desafioverity.presentation.UserUiData
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun Users(state: List<UserEntity>, modifier: Modifier, navigateToDetails: () -> Unit, updateUsers:()->Unit) {
    val lazyListState = rememberLazyListState()
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(state) {
            Card(
                modifier = modifier
                    .clickable { navigateToDetails() }
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Row {
                    AsyncImage(model = it.avatarUrl, contentDescription = "", modifier.size(80.dp))
                    Text(
                        text = it.login,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        textAlign = TextAlign.Justify
                    )
                }
            }
            Pagination(listState = lazyListState) {
                updateUsers()
            }
        }
    }
}

@Composable
fun Pagination(
    listState: LazyListState,
    buffer: Int = 2,
    action: () -> Unit,

    ) {
    var lastTotalItems = -1
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            val loadMore =
                lastVisibleItemIndex > (totalItemsNumber - buffer) && (lastTotalItems != totalItemsNumber)

            loadMore
        }
    }
    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
                    lastTotalItems = listState.layoutInfo.totalItemsCount
                    action()
                }
            }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewUsers() {
    val users = UserEntity(
        login = "Brunoooo",
        id = 222,
        nodeId = "UHASUHAUAHS",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
    )
    val userUiData = UserUiData(
        users = listOf(users)
    )

    Users(state = userUiData.users, Modifier, navigateToDetails = {}) {

    }
}