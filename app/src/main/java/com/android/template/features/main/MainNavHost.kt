package com.android.template.features.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.template.features.taskdetail.TaskDetailScreen
import com.android.template.features.taskdetail.models.TaskDetailDestination
import com.android.template.features.tasks.TaskListScreen
import com.android.template.features.tasks.models.TaskListDestination

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = TaskListDestination,
        modifier = modifier,
    ) {
        composable<TaskListDestination> {
            TaskListScreen(
                navController = navController,
            )
        }

        composable<TaskDetailDestination> {
            TaskDetailScreen(
                navController = navController,
            )
        }
    }
}