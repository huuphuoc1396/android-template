package com.android.template.features.tasks.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.template.domain.models.tasks.Task

@Composable
internal fun TaskList(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
    onClick: (Task) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(tasks.size, key = { index -> tasks[index].id }) { index ->
            val task = tasks[index]
            TaskItem(task = task, onClick = { onClick(task) })
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
internal fun TaskListPreview() {
    TaskList(
        tasks = List(3) { index ->
            Task(
                id = "$index",
                content = "Content $index",
            )
        },
    )
}

