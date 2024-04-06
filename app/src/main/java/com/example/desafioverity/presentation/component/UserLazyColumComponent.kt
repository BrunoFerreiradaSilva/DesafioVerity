package com.example.desafioverity.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.desafioverity.data.model.User

@Composable
fun UserLazyColumComponent(
    users: List<User>,
    modifier: Modifier,
    action: (String) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier.fillMaxWidth()) {
        items(users) {
            Card(
                modifier = modifier
                    .clickable { action(it.login) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Row {
                    AsyncImage(
                        model = it.avatarUrl,
                        contentDescription = "",
                        modifier
                            .size(120.dp)
                            .fillMaxWidth()
                    )
                }
                Row {
                    Text(
                        text = it.login,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}