package com.android.template.features.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.android.template.compose.uistate.models.EmptyEvent
import com.android.template.compose.uistate.viewmodel.UiStateViewModel
import com.android.template.domain.usecases.tasks.GetTaskUseCase
import com.android.template.features.taskdetail.models.TaskDetailDestination
import com.android.template.features.taskdetail.models.TaskDetailUiState
import com.android.template.providers.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatcherProvider: DispatcherProvider,
    private val getTaskUseCase: GetTaskUseCase,
) : UiStateViewModel<TaskDetailUiState, EmptyEvent>(TaskDetailUiState()) {

    init {
        val taskId = savedStateHandle.toRoute<TaskDetailDestination>().taskId
        getTask(taskId)
    }

    private fun getTask(taskId: String) {
        getTaskUseCase(taskId).collectSafe(
            context = dispatcherProvider.io,
            hasLoading = true,
            onError = ::showError,
        ) { task ->
            updateUiState { copy(task = task) }
        }
    }
}