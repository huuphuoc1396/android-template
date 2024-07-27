package com.android.template.data.repositories.tasks

import com.android.template.data.remote.services.TasksService
import com.android.template.data.repositories.tasks.mappers.toTask
import com.android.template.data.repositories.tasks.mappers.toTaskList
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.repositories.tasks.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class TasksRepositoryImpl @Inject constructor(
    private val tasksService: TasksService,
) : TasksRepository {

    override fun getTaskList(): Flow<List<Task>> {
        return flow {
            val taskResponses = tasksService.getTaskList()
            val taskList = taskResponses.toTaskList()
            emit(taskList)
        }
    }

    override fun getTask(id: String): Flow<Task> {
        return flow {
            val taskResponse = tasksService.getTask(id)
            val task = taskResponse.toTask()
            emit(task)
        }
    }

    override suspend fun createTask(task: Task): Task {
        return tasksService.createTask().toTask()
    }
}