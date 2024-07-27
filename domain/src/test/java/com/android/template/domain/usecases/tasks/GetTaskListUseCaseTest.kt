package com.android.template.domain.usecases.tasks

import app.cash.turbine.test
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.repositories.tasks.TasksRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class GetTaskListUseCaseTest {

    private val tasksRepository = mockk<TasksRepository>()
    private val useCase = GetTaskListUseCase(tasksRepository)

    @Test
    fun `getTaskList returns tasks`() = runTest {
        // Given
        val taskList = mockk<List<Task>>()
        every { tasksRepository.getTaskList() } returns flowOf(taskList)

        // When
        val result: Flow<List<Task>> = useCase()

        // Then
        result.test { expectMostRecentItem() shouldBe taskList }
    }
}