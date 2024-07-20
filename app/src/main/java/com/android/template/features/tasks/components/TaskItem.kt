package com.android.template.features.tasks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.template.domain.models.tasks.Task

@Composable
internal fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = task.content,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
internal fun TaskItemPreview() {
    TaskItem(
        task = Task(
            id = "1",
            content = "Content 1",
        )
    )
}