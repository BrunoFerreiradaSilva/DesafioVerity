package com.example.desafioverity.presentation.details

import DialogLimitRequest
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.desafioverity.R
import com.example.desafioverity.domain.helpers.showDataConvertingString
import com.example.desafioverity.domain.model.Repos
import com.example.desafioverity.presentation.component.ErrorScreenState

@Composable
fun UserDetail(
    state: UserDetailUiData,
    modifier: Modifier,
    tryAgain: () -> Unit
) {
    DialogLimitRequest(showDialog = state.limitRequest, dateForRequest = state.dateForNewRequest) {
    }
    if (state.isLoading) {
        UserDetailLoading(modifier = modifier)
    }

    if (state.isError) {
        ErrorScreenState(modifier = modifier) {
            tryAgain()
        }
    }

    if (state.isData) {
        Column(modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
            Card(modifier.padding(bottom = 4.dp)) {
                Row {
                    AsyncImage(
                        model = state.user?.avatarUrl,
                        contentDescription = stringResource(id = R.string.image_user),
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
                                contentDescription = stringResource(R.string.icon_location),
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
                        contentDescription = stringResource(R.string.icon_person),
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
                        text = stringResource(R.string.followers),
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
                        text = stringResource(R.string.following),
                        modifier = modifier.align(Alignment.CenterVertically),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            LazyColumn(modifier = modifier.fillMaxWidth()) {
                items(state.repos) {
                    UserRepository(modifier = modifier, repo = it)
                }
            }
        }
    }
}

@Composable
fun UserRepository(modifier: Modifier, repo: Repos) {
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
                text = repo.name,
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
                text = repo.visibility,
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
                val language = if (repo.language.isNullOrBlank()) "" else repo.language
                if (language.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = stringResource(R.string.icon_check),
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
                    contentDescription = stringResource(R.string.icon_fork_github),
                    modifier.size(14.dp),
                    tint = Color.Gray
                )
                Text(
                    text = repo.forks.toString(),
                    modifier = modifier.padding(horizontal = 2.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
            Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(R.string.icon_star),
                    modifier.size(14.dp),
                    tint = Color.Gray
                )
                Text(
                    text = repo.size.toString(),
                    modifier = modifier.padding(horizontal = 2.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
            Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                Icon(
                    painterResource(id = R.drawable.icon_eye),
                    contentDescription = stringResource(R.string.icon_eye),
                    modifier.size(14.dp),
                    tint = Color.Gray
                )
                Text(
                    text = repo.watchers.toString(),
                    modifier = modifier.padding(horizontal = 2.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
        }
        Row(modifier.padding(start = 8.dp, bottom = 8.dp)) {
            Text(
                text = stringResource(R.string.update_on),
                modifier = modifier.padding(horizontal = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Text(
                text = repo.updatedAt.showDataConvertingString(),
                modifier = modifier.padding(horizontal = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }

    }
}