package com.android.template.features.taskdetail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.template.domain.models.tasks.Task

@Composable
internal fun TaskDetailContent(task: Task, modifier: Modifier = Modifier) {
    Text(
        text = task.content,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Preview
@Composable
internal fun TaskDetailContentPreview() {
    TaskDetailContent(task = Task(content = "Task content"))
}