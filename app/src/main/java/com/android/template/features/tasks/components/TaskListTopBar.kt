package com.android.template.features.tasks.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
internal fun TaskListTopBar(
    modifier: Modifier = Modifier,
    onCreateTaskClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.task_list_title)) },
        modifier = modifier,
        actions = {
            IconButton(onClick = onCreateTaskClick) {
                Icon(Icons.Filled.Add, null)
            }
        },
    )
}

@Preview
@Composable
internal fun TaskListTopBarPreview() {
    TaskListTopBar()
}