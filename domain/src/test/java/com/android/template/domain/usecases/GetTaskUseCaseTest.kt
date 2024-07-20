package com.android.template.domain.usecases

import app.cash.turbine.test
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.repositories.tasks.TasksRepository
import com.android.template.domain.usecases.tasks.GetTaskUseCase
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class GetTaskUseCaseTest {

    private val tasksRepository = mockk<TasksRepository>()
    private val useCase = GetTaskUseCase(tasksRepository)

    @Test
    fun `get task`() = runTest {
        // Given
        val id = "id"
        val task = mockk<Task>()
        every { tasksRepository.getTask(id) } returns flowOf(task)

        // When
        val result = useCase(id)

        // Then
        result.test { expectMostRecentItem() shouldBe task }
    }
}