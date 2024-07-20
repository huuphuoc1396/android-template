package com.android.template.features.taskdetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.template.compose.components.uistate.UiStateScreen
import com.android.template.features.taskdetail.components.TaskDetailContent
import com.android.template.features.taskdetail.components.TaskDetailTopBar

@Composable
internal fun TaskDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel = hiltViewModel(),
) {
    UiStateScreen(viewModel = viewModel) { uiState ->
        Scaffold(
            modifier = modifier,
            topBar = { TaskDetailTopBar(onBackClick = navController::navigateUp) },
        ) { innerPadding ->
            TaskDetailContent(
                task = uiState.task,
                modifier = modifier.padding(innerPadding),
            )
        }
    }
}