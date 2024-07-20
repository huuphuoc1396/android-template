package com.android.template.features.tasks

import app.cash.turbine.test
import com.android.template.compose.uistate.models.ErrorState
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.usecases.tasks.CreateTaskUseCase
import com.android.template.domain.usecases.tasks.GetTaskListUseCase
import com.android.template.features.tasks.models.TaskListUiState
import com.android.template.providers.dispatchers.DispatcherProvider
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TaskListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val dispatcherProvider: DispatcherProvider = mockk()
    private val createTaskUseCase: CreateTaskUseCase = mockk()
    private val getTaskListUseCase: GetTaskListUseCase = mockk()

    private lateinit var taskListViewModel: TaskListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { dispatcherProvider.io } returns testDispatcher
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get task list success`() = runTest {
        // Given
        val tasks = listOf(Task(id = "1", content = "Task 1"), Task(id = "2", content = "Task 2"))
        every { getTaskListUseCase() } returns flowOf(tasks)

        // When
        taskListViewModel = TaskListViewModel(
            dispatcherProvider = dispatcherProvider,
            createTaskUseCase = createTaskUseCase,
            getTaskListUseCase = getTaskListUseCase,
        )
        advanceUntilIdle()

        // Then
        taskListViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskListUiState(tasks = tasks)
        }
    }

    @Test
    fun `get task list fail`() = runTest {
        // Given
        val error = RuntimeException("Error")
        every { getTaskListUseCase() } returns flow { throw error }

        // When
        taskListViewModel = TaskListViewModel(
            dispatcherProvider = dispatcherProvider,
            createTaskUseCase = createTaskUseCase,
            getTaskListUseCase = getTaskListUseCase,
        )
        advanceUntilIdle()

        // Then
        taskListViewModel.error.test {
            expectMostRecentItem() shouldBe ErrorState(message = error.localizedMessage)
        }
    }

    @Test
    fun `create task success`() = runTest {
        // Given
        val tasks = listOf(Task(id = "1", content = "Task 1"), Task(id = "2", content = "Task 2"))
        val newTask = Task()
        every { getTaskListUseCase() } returns flowOf(tasks)
        coEvery { createTaskUseCase(newTask) } returns newTask

        // When
        taskListViewModel = TaskListViewModel(
            dispatcherProvider = dispatcherProvider,
            createTaskUseCase = createTaskUseCase,
            getTaskListUseCase = getTaskListUseCase,
        )
        advanceUntilIdle()

        taskListViewModel.createTask()
        advanceUntilIdle()

        // Then
        taskListViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskListUiState(tasks = tasks + newTask)
        }
    }

    @Test
    fun `create task fail`() = runTest {
        // Given
        val tasks = listOf(Task(id = "1", content = "Task 1"), Task(id = "2", content = "Task 2"))
        val error = RuntimeException("Error")
        val newTask = Task()
        every { getTaskListUseCase() } returns flowOf(tasks)
        coEvery { createTaskUseCase(newTask) } throws error

        // When
        taskListViewModel = TaskListViewModel(
            dispatcherProvider = dispatcherProvider,
            createTaskUseCase = createTaskUseCase,
            getTaskListUseCase = getTaskListUseCase,
        )
        advanceUntilIdle()

        taskListViewModel.createTask()
        advanceUntilIdle()

        // Then
        taskListViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskListUiState(tasks = tasks)
        }
        taskListViewModel.error.test {
            expectMostRecentItem() shouldBe ErrorState(message = "Error")
        }
    }
}