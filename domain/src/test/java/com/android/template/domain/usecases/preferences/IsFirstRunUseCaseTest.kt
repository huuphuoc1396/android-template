package com.android.template.domain.usecases.preferences

import app.cash.turbine.test
import com.android.template.domain.repositories.preferences.PreferencesRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class IsFirstRunUseCaseTest {

    private val preferencesRepository = mockk<PreferencesRepository>()
    private val useCase = IsFirstRunUseCase(preferencesRepository)

    @Test
    fun `isFirstRun returns true`() = runTest {
        // Given
        val expected = true
        every { preferencesRepository.isFirstRun() } returns flowOf(expected)

        // When
        val result = useCase()

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }

    @Test
    fun `isFirstRun returns false`() = runTest {
        // Given
        val expected = false
        every { preferencesRepository.isFirstRun() } returns flowOf(expected)

        // When
        val result = useCase()

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }
}