package com.android.template.features.tasks

import app.cash.turbine.test
import com.android.template.compose.uistate.mappers.toErrorState
import com.android.template.domain.models.errors.NoConnectionException
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.usecases.preferences.IsFirstRunUseCase
import com.android.template.domain.usecases.preferences.SetFirstRunUseCase
import com.android.template.domain.usecases.tasks.CreateTaskUseCase
import com.android.template.domain.usecases.tasks.GetTaskListUseCase
import com.android.template.features.tasks.models.TaskListEvent
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
    private val isFirstRunUseCase: IsFirstRunUseCase = mockk()
    private val setFirstRunUseCase: SetFirstRunUseCase = mockk()

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
    fun `getTaskList is success`() = runTest {
        // Given
        val tasks = giveTaskList()

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        // Then
        taskListViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskListUiState(tasks = tasks)
        }
    }

    @Test
    fun `getTaskList is fail`() = runTest {
        // Given
        val error = NoConnectionException()
        val errorState = error.toErrorState()
        every { getTaskListUseCase() } returns flow { throw error }

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        // Then
        taskListViewModel.error.test {
            expectMostRecentItem() shouldBe errorState
        }
    }

    @Test
    fun `createTask is success`() = runTest {
        // Given
        val tasks = giveTaskList()

        val newTask = Task()
        coEvery { createTaskUseCase(newTask) } returns newTask

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        taskListViewModel.createTask()
        advanceUntilIdle()

        // Then
        taskListViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskListUiState(tasks = tasks + newTask)
        }
    }

    @Test
    fun `createTask is fail`() = runTest {
        // Given
        val tasks = giveTaskList()

        val error = NoConnectionException()
        val errorState = error.toErrorState()
        val newTask = Task()
        coEvery { createTaskUseCase(newTask) } throws error

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        taskListViewModel.createTask()
        advanceUntilIdle()

        // Then
        taskListViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskListUiState(tasks = tasks)
        }
        taskListViewModel.error.test {
            expectMostRecentItem() shouldBe errorState
        }
    }

    @Test
    fun `isFirstRun is true`() = runTest {
        // Given
        giveTaskList()

        val expected = true
        every { isFirstRunUseCase() } returns flowOf(expected)
        coEvery { setFirstRunUseCase(false) } returns Unit

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        // Then
        taskListViewModel.singleEvent.test {
            expectMostRecentItem() shouldBe TaskListEvent.FirstRun
        }
    }

    @Test
    fun `isFirstRun is false`() = runTest {
        // Given
        giveTaskList()

        val expected = false
        every { isFirstRunUseCase() } returns flowOf(expected)

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        // Then
        taskListViewModel.singleEvent.test {
            expectNoEvents()
        }
    }

    @Test
    fun `openTaskDetail calls`() = runTest {
        // Given
        val tasks = giveTaskList()

        // When
        taskListViewModel = createTaskListViewModel()
        advanceUntilIdle()

        taskListViewModel.openTaskDetail(tasks.first())
        advanceUntilIdle()

        // Then
        taskListViewModel.singleEvent.test {
            expectMostRecentItem() shouldBe TaskListEvent.OpenTaskDetail(taskId = tasks.first().id)
        }
    }

    private fun giveTaskList(): List<Task> {
        val tasks = listOf(Task(id = "1", content = "Task 1"), Task(id = "2", content = "Task 2"))
        every { getTaskListUseCase() } returns flowOf(tasks)
        return tasks
    }

    private fun createTaskListViewModel() = TaskListViewModel(
        dispatcherProvider = dispatcherProvider,
        createTaskUseCase = createTaskUseCase,
        getTaskListUseCase = getTaskListUseCase,
        setFirstRunUseCase = setFirstRunUseCase,
        isFirstRunUseCase = isFirstRunUseCase,
    )
}