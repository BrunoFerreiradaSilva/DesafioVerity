package com.example.desafioverity.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioverity.R

@Composable
fun ErrorScreenState(modifier: Modifier, tryAgain: () -> Unit) {
    Column(modifier.fillMaxSize()) {
        Column(
            modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = stringResource(R.string.icon_error),
                tint = Color.Red,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .size(120.dp)
            )
            Row(
                modifier
                    .fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.error),
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    modifier = modifier.fillMaxWidth()
                )
            }
            OutlinedButton(
                onClick = { tryAgain() },
                modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }

    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewErrorScreen() {
    ErrorScreenState(Modifier) {

    }
}