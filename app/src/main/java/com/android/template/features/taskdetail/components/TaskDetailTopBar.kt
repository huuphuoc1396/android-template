package com.android.template.features.taskdetail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.template.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TaskDetailTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.task_detail_title)) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
            }
        },
    )
}

@Preview
@Composable
internal fun TaskDetailTopBarPreview() {
    TaskDetailTopBar()
}