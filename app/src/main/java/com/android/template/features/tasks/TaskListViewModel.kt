package com.android.template.features.tasks

import com.android.template.compose.uistate.viewmodel.UiStateViewModel
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.usecases.tasks.CreateTaskUseCase
import com.android.template.domain.usecases.tasks.GetTaskListUseCase
import com.android.template.extenstions.errors.toErrorState
import com.android.template.features.tasks.models.TaskListEvent
import com.android.template.features.tasks.models.TaskListUiState
import com.android.template.providers.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TaskListViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTaskListUseCase: GetTaskListUseCase,
) : UiStateViewModel<TaskListUiState, TaskListEvent>(TaskListUiState()) {

    init {
        getTaskList()
    }

    private fun getTaskList() {
        getTaskListUseCase().collectSafe(
            context = dispatcherProvider.io,
            hasLoading = true,
            onError = { throwable -> showError(throwable.toErrorState()) },
        ) { tasks ->
            updateUiState { copy(tasks = tasks) }
        }
    }

    fun createTask() {
        launchSafe(
            context = dispatcherProvider.io,
            hasLoading = true,
            onError = { throwable -> showError(throwable.toErrorState()) },
        ) {
            val createdTask = createTaskUseCase(Task())
            updateUiState { copy(tasks = tasks + createdTask) }
        }
    }

    fun openTaskDetail(task: Task) {
        sendEvent(TaskListEvent.OpenTaskDetail(task.id))
    }
}