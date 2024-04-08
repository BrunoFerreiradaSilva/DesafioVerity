package com.example.desafioverity.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun UserDetailLoading(
    modifier: Modifier
) {
    Column(modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
        Card(modifier.shimmer()
            .fillMaxWidth()) {
            Spacer(
                modifier
                    .background(Color.Gray)
                    .shimmer()
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(percent = 50))
            )
        }
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            val users = mutableListOf<Int>()
            for (i in 0..10) {
                users.add(i)
            }
            items(users) {
                Card (modifier.shimmer()
                    .fillMaxWidth().padding(top = 8.dp)) {
                    Spacer(
                        modifier = modifier
                            .background(Color.Gray)
                            .shimmer()
                            .fillMaxWidth()
                            .size(80.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewUserDetailLoading() {
    UserDetailLoading(modifier = Modifier)
}