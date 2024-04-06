package com.example.desafioverity.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.desafioverity.R
import com.example.desafioverity.data.model.User
import com.example.desafioverity.data.model.UserDetail
import com.example.desafioverity.presentation.users.Pagination

@Composable
fun UserDetail(user: UserDetail?, modifier: Modifier, goToRepositoryList: () -> Unit) {
    Column(modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
        Card(modifier.padding(bottom = 4.dp)) {
            Row {
                AsyncImage(
                    model = user?.avatarUrl, contentDescription = "",
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
                    Row(modifier.padding(horizontal = 8.dp)) {
                        Text(
                            text = "Name: ",
                            modifier = modifier.align(Alignment.CenterVertically),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        user?.name?.let {
                            Text(
                                text = it,
                                modifier = modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Row(modifier.padding(horizontal = 9.dp)) {
                        Text(
                            text = "Company: ",
                            modifier = modifier.align(Alignment.CenterVertically),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        user?.company?.let {
                            Text(
                                text = it,
                                modifier = modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Row(modifier.padding(horizontal = 8.dp)) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "",
                            modifier = modifier
                                .size(16.dp)
                                .align(Alignment.CenterVertically)
                        )
                        user?.location?.let {
                            Text(
                                text = it,
                                modifier = modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp,
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
                user?.followers?.let {
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
                user?.following?.let {
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
            val repos = listOf("aaaaaaa","bbbbbbbb","ccccccccc")
            items(repos) {
                Card(
                    modifier = modifier
                        .clickable { goToRepositoryList()}
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row {
                        Text(
                            text = it,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 8.dp),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewUserDetail() {
    val userDetail = UserDetail(
        login = "",
        avatarUrl = "",
        name = "Bruno Ferreira",
        company = "Verity",
        location = "Florianopolis - SC",
        publicRepos = 55,
        followers = 7,
        following = 2
    )
    UserDetail(user = userDetail, modifier = Modifier) {

    }
}