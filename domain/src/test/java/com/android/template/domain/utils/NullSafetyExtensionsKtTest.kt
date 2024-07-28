package com.android.template.domain.utils

import io.kotest.matchers.shouldBe
import org.junit.Test

internal class NullSafetyExtensionsKtTest {

    @Test
    fun `Boolean orFalse returns default`() {
        // Given
        val nullableBoolean: Boolean? = null

        // When
        val result = nullableBoolean.orFalse()

        // Then
        result shouldBe false
    }

    @Test
    fun `Boolean orFalse returns value`() {
        // Given
        val nullableBoolean = true

        // When
        val result = nullableBoolean.orFalse()

        // Then
        result shouldBe true
    }

    @Test
    fun `Boolean orTrue returns default`() {
        // Given
        val nullableBoolean: Boolean? = null

        // When
        val result = nullableBoolean.orTrue()

        // Then
        result shouldBe true
    }

    @Test
    fun `Boolean orTrue returns value`() {
        // Given
        val nullableBoolean = false

        // When
        val result = nullableBoolean.orTrue()

        // Then
        result shouldBe false
    }

    @Test
    fun `String orEmpty returns default`() {
        // Given
        val nullableString: String? = null

        // When
        val result = nullableString.orEmpty()

        // Then
        result shouldBe ""
    }

    @Test
    fun `String orEmpty returns value`() {
        // Given
        val nullableString = "Hello"

        // When
        val result = nullableString.orEmpty()

        // Then
        result shouldBe "Hello"
    }

    @Test
    fun `Int orZero returns default`() {
        // Given
        val nullableInt: Int? = null

        // When
        val result = nullableInt.orZero()

        // Then
        result shouldBe 0
    }

    @Test
    fun `Int orZero returns value`() {
        // Given
        val nullableInt = 10

        // When
        val result = nullableInt.orZero()

        // Then
        result shouldBe 10
    }

    @Test
    fun `Long orZero returns default`() {
        // Given
        val nullableLong: Long? = null

        // When
        val result = nullableLong.orZero()

        // Then
        result shouldBe 0L
    }

    @Test
    fun `Long orZero returns value`() {
        // Given
        val nullableLong = 10L

        // When
        val result = nullableLong.orZero()

        // Then
        result shouldBe 10L
    }

    @Test
    fun `Float orZero returns default`() {
        // Given
        val nullableFloat: Float? = null

        // When
        val result = nullableFloat.orZero()

        // Then
        result shouldBe 0f
    }

    @Test
    fun `Float orZero returns value`() {
        // Given
        val nullableFloat = 10f

        // When
        val result = nullableFloat.orZero()

        // Then
        result shouldBe 10f
    }

    @Test
    fun `Double orZero returns default`() {
        // Given
        val nullableDouble: Double? = null

        // When
        val result = nullableDouble.orZero()

        // Then
        result shouldBe 0.0
    }

    @Test
    fun `Double orZero returns value`() {
        // Given
        val nullableDouble = 10.0

        // When
        val result = nullableDouble.orZero()

        // Then
        result shouldBe 10.0
    }

    @Test
    fun `List orEmpty returns default`() {
        // Given
        val nullableList: List<String>? = null

        // When
        val result = nullableList.orEmpty()

        // Then
        result shouldBe emptyList()
    }

    @Test
    fun `List orEmpty returns value`() {
        // Given
        val nullableList = listOf("Hello")

        // When
        val result = nullableList.orEmpty()

        // Then
        result shouldBe listOf("Hello")
    }

    @Test
    fun `Map orEmpty returns default`() {
        // Given
        val nullableMap: Map<String, String>? = null

        // When
        val result = nullableMap.orEmpty()

        // Then
        result shouldBe emptyMap()
    }

    @Test
    fun `Map orEmpty returns value`() {
        // Given
        val nullableMap = mapOf("key" to "value")

        // When
        val result = nullableMap.orEmpty()

        // Then
        result shouldBe mapOf("key" to "value")
    }
}