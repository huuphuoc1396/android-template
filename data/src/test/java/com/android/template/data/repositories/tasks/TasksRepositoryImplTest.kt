package com.android.template.data.repositories.tasks

import app.cash.turbine.test
import com.android.template.data.remote.models.responses.tasks.TaskResponse
import com.android.template.data.remote.services.TasksService
import com.android.template.data.repositories.tasks.mappers.toTask
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class TasksRepositoryImplTest {

    private val tasksService: TasksService = mockk()
    private val repository = TasksRepositoryImpl(tasksService)

    @Test
    fun `getTaskList returns tasks`() = runTest {
        // Given
        val mockResponses = listOf(TaskResponse())
        val expected = mockResponses.map { it.toTask() }
        coEvery { tasksService.getTaskList() } returns mockResponses

        // When
        val result = repository.getTaskList()

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }

    @Test
    fun `getTask returns task`() = runTest {
        // Given
        val id = "id"
        val mockResponse = TaskResponse()
        val expected = mockResponse.toTask()
        coEvery { tasksService.getTask(id) } returns mockResponse

        // When
        val result = repository.getTask(id)

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }

    @Test
    fun `createTask returns task`() = runTest {
        // Given
        val response = TaskResponse()
        val task = response.toTask()
        coEvery { tasksService.createTask() } returns response

        // When
        val result = repository.createTask(task)

        // Then
        result shouldBe task
    }
}