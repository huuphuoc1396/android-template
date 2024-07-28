package com.android.template.data.repositories.tasks.mappers

import com.android.template.data.remote.models.responses.tasks.TaskResponse
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.utils.orEmpty

internal fun TaskResponse.toTask() = Task(
    id = id.orEmpty(),
    content = content.orEmpty(),
)

internal fun List<TaskResponse>.toTaskList() = map { response -> response.toTask() }