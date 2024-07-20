package com.android.template.domain.repositories.tasks

import com.android.template.domain.models.tasks.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getTaskList(): Flow<List<Task>>

    fun getTask(id: String): Flow<Task>

    suspend fun createTask(task: Task): Task
}