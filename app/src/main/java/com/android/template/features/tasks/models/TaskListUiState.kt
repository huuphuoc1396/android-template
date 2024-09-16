package com.android.template.features.tasks.models

import com.android.template.domain.models.tasks.Task
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

internal data class TaskListUiState(
    val tasks: PersistentList<Task> = persistentListOf(),
)