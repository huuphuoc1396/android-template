package com.android.template.data.remote.adapters.errors

import androidx.datastore.preferences.protobuf.Any
import com.android.template.domain.models.errors.NoConnectionException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import retrofit2.Callback
import java.net.UnknownHostException

class ErrorHandlingCallbackTest {

    private val response = mockk<retrofit2.Response<Any>>()
    private val call = mockk<retrofit2.Call<Any>>()
    private val delegate = mockk<Callback<Any>>()
    private val errorHandlingCallback = ErrorHandlingCallback(delegate)

    @Test
    fun `onResponse should call delegate onResponse when response is successful`() {
        // Given
        every { response.isSuccessful } returns true
        every { delegate.onResponse(call, response) } returns Unit

        // When
        errorHandlingCallback.onResponse(call, response)

        // Then
        verify { delegate.onResponse(call, response) }
    }

    @Test
    fun `onResponse should call delegate onFailure with throwable for unsuccessful response`() {
        // Given
        every { response.isSuccessful } returns false
        every { response.code() } returns 500
        every { response.errorBody()?.string() } returns "error"
        every { delegate.onFailure(call, any()) } returns Unit

        // When
        errorHandlingCallback.onResponse(call, response)

        // Then
        verify { delegate.onFailure(call, any()) }
    }

    @Test
    fun `onFailure should call delegate onFailure with NoConnectionException for UnknownHostException`() {
        // Given
        val unknownHostException = mockk<UnknownHostException>()
        every { response.isSuccessful } returns false
        every { delegate.onFailure(call, any()) } returns Unit

        // When
        errorHandlingCallback.onFailure(call, unknownHostException)

        // Then
        verify { delegate.onFailure(call, NoConnectionException()) }
    }

    @Test
    fun `onFailure should call delegate onFailure with NoConnectionException for InterruptedIOException`() {
        // Given
        val interruptedIOException = mockk<java.io.InterruptedIOException>()
        every { response.isSuccessful } returns false
        every { delegate.onFailure(call, any()) } returns Unit

        // When
        errorHandlingCallback.onFailure(call, interruptedIOException)

        // Then
        verify { delegate.onFailure(call, NoConnectionException()) }
    }

    @Test
    fun `onFailure should call delegate onFailure with throwable for other exceptions`() {
        // Given
        val throwable = mockk<Throwable>()
        every { response.isSuccessful } returns false
        every { delegate.onFailure(call, any()) } returns Unit

        // When
        errorHandlingCallback.onFailure(call, throwable)

        // Then
        verify { delegate.onFailure(call, throwable) }
    }
}