package com.android.template.domain.usecases.preferences

import com.android.template.domain.repositories.preferences.PreferencesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class SetFirstRunUseCaseTest {

    private val preferencesRepository = mockk<PreferencesRepository>()
    private val useCase = SetFirstRunUseCase(preferencesRepository)

    @Test
    fun `setFirstRun is called with true`() = runTest{
        // Given
        val expected = true
        coEvery { preferencesRepository.setFirstRun(expected) } returns Unit

        // When
        useCase(expected)

        // Then
        coVerify { preferencesRepository.setFirstRun(expected) }
    }

    @Test
    fun `setFirstRun is called with false`() = runTest{
        // Given
        val expected = false
        coEvery { preferencesRepository.setFirstRun(expected) } returns Unit

        // When
        useCase(expected)

        // Then
        coVerify { preferencesRepository.setFirstRun(expected) }
    }
}