package com.android.template.features.tasks

import com.android.template.compose.uistate.viewmodel.UiStateViewModel
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.usecases.preferences.IsFirstRunUseCase
import com.android.template.domain.usecases.preferences.SetFirstRunUseCase
import com.android.template.domain.usecases.tasks.CreateTaskUseCase
import com.android.template.domain.usecases.tasks.GetTaskListUseCase
import com.android.template.domain.utils.orTrue
import com.android.template.extenstions.errors.toErrorState
import com.android.template.features.tasks.models.TaskListEvent
import com.android.template.features.tasks.models.TaskListUiState
import com.android.template.providers.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
internal class TaskListViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTaskListUseCase: GetTaskListUseCase,
    private val isFirstRunUseCase: IsFirstRunUseCase,
    private val setFirstRunUseCase: SetFirstRunUseCase,
) : UiStateViewModel<TaskListUiState, TaskListEvent>(TaskListUiState()) {

    init {
        getTaskList()
        checkFirstRun()
    }

    private fun checkFirstRun() {
        launchSafe {
            val isFirstRun = isFirstRunUseCase().firstOrNull().orTrue()
            if (isFirstRun) {
                setFirstRunUseCase(false)
                sendEvent(TaskListEvent.FirstRun)
            }
        }
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