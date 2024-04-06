package com.example.desafioverity.presentation.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.desafioverity.R
import com.example.desafioverity.domain.helpers.showDataConvertingString
import com.example.desafioverity.presentation.stateScreen.error.ErrorScreenState
import com.example.desafioverity.presentation.stateScreen.loading.UserDetailLoading

@Composable
fun UserDetail(
    state: UserDetailUiData,
    modifier: Modifier,
    tryAgain: () -> Unit
) {
    if (state.isLoading){
        UserDetailLoading(modifier = modifier)
    }

    if (state.isError){
        ErrorScreenState(modifier = modifier){
            tryAgain()
        }
    }

    if (state.isData) {
        Column(modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
            Card(modifier.padding(bottom = 4.dp)) {
                Row {
                    AsyncImage(
                        model = state.user?.avatarUrl, contentDescription = "",
                        modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(percent = 50))
                    )
                    Column(
                        modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp)
                    ) {
                        Row(modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                            state.user?.name?.let {
                                Text(
                                    text = it,
                                    modifier = modifier.align(Alignment.CenterVertically),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Row {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "",
                                modifier = modifier
                                    .padding(horizontal = 8.dp)
                                    .size(14.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            state.user?.location?.let {
                                Text(
                                    text = it,
                                    modifier = modifier.align(Alignment.CenterVertically),
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                    }
                }
                Row(modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier.size(20.dp)
                    )
                    state.user?.followers?.let {
                        Text(
                            text = it.toString(),
                            modifier = modifier.align(Alignment.CenterVertically),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = " Followers - ",
                        modifier = modifier.align(Alignment.CenterVertically),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    state.user?.following?.let {
                        Text(
                            text = it.toString(),
                            modifier = modifier.align(Alignment.CenterVertically),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = " Following",
                        modifier = modifier.align(Alignment.CenterVertically),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            LazyColumn(modifier = modifier.fillMaxWidth()) {
                items(state.repos) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Row(
                            modifier
                                .padding(vertical = 4.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = it.name,
                                modifier = modifier
                                    .padding(horizontal = 10.dp, vertical = 2.dp),
                                textAlign = TextAlign.Center
                            )
                            Spacer(
                                modifier = modifier
                                    .weight(1f)
                                    .height(0.dp)
                            )
                            Text(
                                text = it.visibility,
                                modifier = modifier
                                    .border(
                                        width = 2.dp,
                                        color = Color.Gray,
                                        shape = ShapeDefaults.ExtraLarge
                                    )
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = modifier.padding(end = 12.dp))
                        }
                        Row {
                            Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                                val language = if (it.language.isNullOrBlank()) "" else it.language
                                if (language.isNotEmpty()) {
                                    Icon(
                                        imageVector = Icons.Outlined.Check,
                                        contentDescription = "",
                                        modifier.size(14.dp),
                                        tint = Color.Gray
                                    )
                                    Text(
                                        text = language,
                                        modifier = modifier.padding(horizontal = 2.dp),
                                        textAlign = TextAlign.Center,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                            Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                                Icon(
                                    painterResource(id = R.drawable.git_fork_svgrepo_com),
                                    contentDescription = "",
                                    modifier.size(14.dp),
                                    tint = Color.Gray
                                )
                                Text(
                                    text = it.forks.toString(),
                                    modifier = modifier.padding(horizontal = 2.dp),
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp
                                )
                            }
                            Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "",
                                    modifier.size(14.dp),
                                    tint = Color.Gray
                                )
                                Text(
                                    text = it.size.toString(),
                                    modifier = modifier.padding(horizontal = 2.dp),
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp
                                )
                            }
                            Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                                Icon(
                                    painterResource(id = R.drawable.icon_eye),
                                    contentDescription = "",
                                    modifier.size(14.dp),
                                    tint = Color.Gray
                                )
                                Text(
                                    text = it.watchers.toString(),
                                    modifier = modifier.padding(horizontal = 2.dp),
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                            Text(
                                text = "Update on",
                                modifier = modifier.padding(horizontal = 2.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp
                            )
                            Text(
                                text = it.updatedAt.showDataConvertingString(),
                                modifier = modifier.padding(horizontal = 2.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp
                            )
                        }

                    }
                }
            }
        }
    }
}