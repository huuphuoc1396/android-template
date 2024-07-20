package com.android.template.domain.usecases

import com.android.template.domain.models.tasks.Task
import com.android.template.domain.repositories.tasks.TasksRepository
import com.android.template.domain.usecases.tasks.CreateTaskUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class CreateTaskUseCaseTest {

    private val tasksRepository = mockk<TasksRepository>()
    private val useCase = CreateTaskUseCase(tasksRepository)

    @Test
    fun `create task`() = runTest {
        // Given
        val task = mockk<Task>()
        coEvery { tasksRepository.createTask(task) } returns task

        // When
        val result = useCase(task)

        // Then
        result shouldBe task
    }
}