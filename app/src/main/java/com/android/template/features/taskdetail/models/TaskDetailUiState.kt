package com.android.template.features.taskdetail.models

import com.android.template.domain.models.tasks.Task

internal data class TaskDetailUiState(
    val task: Task = Task(),
)