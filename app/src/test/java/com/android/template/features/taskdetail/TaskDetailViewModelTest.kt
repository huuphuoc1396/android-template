package com.android.template.features.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.android.template.compose.uistate.mappers.toErrorState
import com.android.template.domain.models.errors.NoConnectionException
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.usecases.tasks.GetTaskUseCase
import com.android.template.features.taskdetail.models.TaskDetailDestination
import com.android.template.features.taskdetail.models.TaskDetailUiState
import com.android.template.providers.dispatchers.DispatcherProvider
import com.android.template.utils.mockkSavedStateHandle
import com.android.template.utils.unmockkSavedStateHandle
import io.kotest.matchers.shouldBe
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
internal class TaskDetailViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)
    private val dispatcherProvider: DispatcherProvider = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private lateinit var taskDetailViewModel: TaskDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkSavedStateHandle()
        every { dispatcherProvider.io } returns testDispatcher
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkSavedStateHandle()
    }

    @Test
    fun `getTask is success`() = runTest {
        // Given
        val task = Task(id = "1", content = "Task 1")
        every { getTaskUseCase("1") } returns flowOf(task)
        every {
            savedStateHandle.toRoute<TaskDetailDestination>()
        } returns TaskDetailDestination(taskId = "1")

        // When
        taskDetailViewModel = createTaskDetailViewModel()
        advanceUntilIdle()

        // Then
        taskDetailViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskDetailUiState(task = task)
        }
    }

    @Test
    fun `getTask is fail`() = runTest {
        // Given
        val error = NoConnectionException()
        val errorState = error.toErrorState()
        every { getTaskUseCase("1") } returns flow { throw error }
        every {
            savedStateHandle.toRoute<TaskDetailDestination>()
        } returns TaskDetailDestination(taskId = "1")

        // When
        taskDetailViewModel = createTaskDetailViewModel()
        advanceUntilIdle()

        // Then
        taskDetailViewModel.error.test {
            expectMostRecentItem() shouldBe errorState
        }
    }

    private fun createTaskDetailViewModel() = TaskDetailViewModel(
        savedStateHandle = savedStateHandle,
        dispatcherProvider = dispatcherProvider,
        getTaskUseCase = getTaskUseCase,
    )
}