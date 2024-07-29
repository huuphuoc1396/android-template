package com.android.template.data.repositories.preferences

import app.cash.turbine.test
import com.android.template.data.storages.datastore.preferences.PreferencesDataStore
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class PreferencesRepositoryImplTest {

    private val preferencesDataStore = mockk<PreferencesDataStore>()
    private val repository = PreferencesRepositoryImpl(preferencesDataStore)

    @Test
    fun `isFirstRun returns true`() = runTest {
        // Given
        val expected = true
        every { preferencesDataStore.isFirstRun() } returns flowOf(expected)

        // When
        val result = repository.isFirstRun()

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }

    @Test
    fun `isFirstRun returns false`() = runTest {
        // Given
        val expected = false
        every { preferencesDataStore.isFirstRun() } returns flowOf(expected)

        // When
        val result = repository.isFirstRun()

        // Then
        result.test {
            expectMostRecentItem() shouldBe expected
        }
    }

    @Test
    fun `setFirstRun is true`() = runTest {
        // Given
        val isFirstRun = true
        coEvery { preferencesDataStore.setFirstRun(isFirstRun) } returns Unit

        // When
        repository.setFirstRun(isFirstRun)

        // Then
        coVerify { preferencesDataStore.setFirstRun(isFirstRun) }
    }

    @Test
    fun `setFirstRun is false`() = runTest {
        // Given
        val isFirstRun = false
        coEvery { preferencesDataStore.setFirstRun(isFirstRun) } returns Unit

        // When
        repository.setFirstRun(isFirstRun)

        // Then
        coVerify { preferencesDataStore.setFirstRun(isFirstRun) }
    }
}