package com.android.template.data.remote.adapters.mappers

import com.android.template.domain.models.errors.ApiException
import com.android.template.domain.models.errors.UnauthorizedException
import com.google.gson.JsonSyntaxException
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import retrofit2.Response

internal class ErrorMappersKtTest {

    @Test
    fun `toThrowable returns UnauthorizedException for 401 response`() {
        // Given
        val response = mockk<Response<*>>()
        val code = 401
        every { response.code() } returns code

        // When
        val result = response.toThrowable()

        // Then
        result shouldBe UnauthorizedException()
    }

    @Test
    fun `toThrowable returns UnauthorizedException for 404 response`() {
        // Given
        val response = mockk<Response<*>>()
        val code = 404
        val json = "{\"code\":404,\"message\":\"Not Found\"}"
        every { response.code() } returns code
        every { response.errorBody()?.string() } returns json

        // When
        val result = response.toThrowable()

        // Then
        result shouldBe ApiException(code = 404, message = "Not Found")
    }

    @Test
    fun `toThrowable returns JsonSyntaxException for parsing error`() {
        // Given
        val response = mockk<Response<*>>()
        val code = 404
        val json = "error"
        every { response.code() } returns code
        every { response.errorBody()?.string() } returns json

        // When
        val result = response.toThrowable()

        // Then
        result should beInstanceOf<JsonSyntaxException>()
    }

    @Test
    fun `toThrowable returns default ApiException for empty response`() {
        // Given
        val response = mockk<Response<*>>()
        val code = 404
        val json = "{}"
        every { response.code() } returns code
        every { response.errorBody()?.string() } returns json

        // When
        val result = response.toThrowable()

        // Then
        result shouldBe ApiException()
    }

    @Test
    fun `toThrowable returns default ApiException for null errorBody`() {
        // Given
        val response = mockk<Response<*>>()
        val code = 404
        every { response.code() } returns code
        every { response.errorBody() } returns null

        // When
        val result = response.toThrowable()

        // Then
        result shouldBe ApiException()
    }
}