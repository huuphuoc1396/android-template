package com.android.template.features.tasks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.template.compose.components.uistate.UiStateScreen
import com.android.template.features.taskdetail.models.TaskDetailDestination
import com.android.template.features.tasks.components.TaskList
import com.android.template.features.tasks.components.TaskListTopBar
import com.android.template.features.tasks.models.TaskListEvent

@Composable
internal fun TaskListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: TaskListViewModel = hiltViewModel(),
) {
    UiStateScreen(
        viewModel = viewModel,
        onEvent = { event ->
            handleEvent(
                event = event,
                navController = navController,
            )
        },
    ) { uiState ->
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = { TaskListTopBar(onCreateTaskClick = viewModel::createTask) },
        ) { innerPadding ->
            TaskList(
                tasks = uiState.tasks,
                modifier = Modifier.padding(innerPadding),
                onClick = viewModel::openTaskDetail
            )
        }
    }
}

private fun handleEvent(
    event: TaskListEvent,
    navController: NavController,
) {
    when (event) {
        is TaskListEvent.OpenTaskDetail -> {
            navController.navigate(TaskDetailDestination(taskId = event.taskId))
        }
    }
}

