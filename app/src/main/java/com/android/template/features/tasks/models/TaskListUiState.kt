package com.android.template.features.tasks.models

import com.android.template.domain.models.tasks.Task

internal data class TaskListUiState(
    val tasks: List<Task> = listOf(),
)