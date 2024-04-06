package com.example.desafioverity.presentation.stateScreen.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier.shimmer().fillMaxWidth()) {
            val users = mutableListOf<Int>()
            for (i in 0..14){
                users.add(i)
            }
            items(users) {
                Card(
                    modifier = modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row {
                        Spacer(
                            modifier
                                .background(Color.Gray)
                                .size(120.dp)
                                .fillMaxWidth()
                        )
                    }
                    Row {
                        Spacer(
                            modifier = modifier
                                .background(Color.Gray)
                                .align(Alignment.CenterVertically)
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()

                        )
                    }
                }
            }
        }

    }

}

@Composable
@Preview(showSystemUi = true)
fun PrevierLazy(){
    UsersLoading(modifier = Modifier)
}