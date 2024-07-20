package com.android.template.domain.usecases.tasks

import com.android.template.domain.models.tasks.Task
import com.android.template.domain.repositories.tasks.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) {

    operator fun invoke(id: String): Flow<Task> = tasksRepository.getTask(id)
}