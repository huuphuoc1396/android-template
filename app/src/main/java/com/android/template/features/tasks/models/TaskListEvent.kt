package com.android.template.features.tasks.models

internal sealed class TaskListEvent {

    data class OpenTaskDetail(val taskId: String) : TaskListEvent()
}