package com.example.desafioverity.presentation.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun UsersLoading(modifier: Modifier) {
    Column {
        Card(
            modifier = modifier
                .shimmer()
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
        ) {
            Row {
                Spacer(
                    modifier
                        .background(Color.Gray)
                        .shimmer()
                        .fillMaxWidth()
                        .size(60.dp)
                )
            }
        }
        LazyVerticalGridUserLoading(modifier = modifier)
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewLazy(){
    UsersLoading(modifier = Modifier)
}