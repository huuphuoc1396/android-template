package com.android.template.compose.components.loading

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(false) {},
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            strokeCap = StrokeCap.Round,
            strokeWidth = 4.dp,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )
    }
}

@Preview
@Composable
internal fun LoadingPreview() {
    Loading()
}