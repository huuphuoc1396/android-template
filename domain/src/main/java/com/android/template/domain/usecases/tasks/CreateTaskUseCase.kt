package com.android.template.domain.usecases.tasks

import com.android.template.domain.models.tasks.Task
import com.android.template.domain.repositories.tasks.TasksRepository
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) {

    suspend operator fun invoke(task: Task): Task = tasksRepository.createTask(task)
}