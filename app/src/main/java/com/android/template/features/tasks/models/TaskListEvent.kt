package com.android.template.features.tasks.models

internal sealed class TaskListEvent {

    data object FirstRun : TaskListEvent()

    data class OpenTaskDetail(val taskId: String) : TaskListEvent()
}