package com.android.template.data.repositories.mappers.tasks

import com.android.template.data.remote.models.responses.tasks.TaskResponse
import com.android.template.domain.models.tasks.Task

internal fun TaskResponse.toTask() = Task(
    id = id.orEmpty(),
    content = content.orEmpty(),
)