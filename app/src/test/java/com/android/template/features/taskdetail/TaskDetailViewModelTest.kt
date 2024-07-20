package com.android.template.features.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.android.template.domain.models.tasks.Task
import com.android.template.domain.usecases.tasks.GetTaskUseCase
import com.android.template.features.taskdetail.models.TaskDetailDestination
import com.android.template.features.taskdetail.models.TaskDetailUiState
import com.android.template.providers.dispatchers.DispatcherProvider
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
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
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { dispatcherProvider.io } returns testDispatcher
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    @Test
    fun `get task success`() = runTest {
        // Given
        val task = Task(id = "1", content = "Task 1")
        every { getTaskUseCase("1") } returns flowOf(task)
        every {
            savedStateHandle.toRoute<TaskDetailDestination>()
        } returns TaskDetailDestination(taskId = "1")

        // When
        taskDetailViewModel = TaskDetailViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcherProvider,
            getTaskUseCase = getTaskUseCase,
        )
        advanceUntilIdle()

        // Then
        taskDetailViewModel.uiState.test {
            expectMostRecentItem() shouldBe TaskDetailUiState(task = task)
        }
    }

    @Test
    fun `get task fail`() = runTest {
        // Given
        val throwable = Throwable("Error")
        every { getTaskUseCase("1") } returns flow { throw throwable }
        every {
            savedStateHandle.toRoute<TaskDetailDestination>()
        } returns TaskDetailDestination(taskId = "1")

        // When
        taskDetailViewModel = TaskDetailViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcherProvider,
            getTaskUseCase = getTaskUseCase,
        )
        advanceUntilIdle()

        // Then
        taskDetailViewModel.error.test {
            expectMostRecentItem().message shouldBe "Error"
        }
    }
}