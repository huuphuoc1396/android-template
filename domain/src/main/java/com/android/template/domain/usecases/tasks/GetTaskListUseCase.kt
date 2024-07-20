package com.android.template.domain.usecases.tasks

import com.android.template.domain.repositories.tasks.TasksRepository
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {

    operator fun invoke() = tasksRepository.getTaskList()
}