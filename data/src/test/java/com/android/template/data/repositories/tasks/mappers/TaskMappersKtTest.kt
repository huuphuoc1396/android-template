package com.android.template.data.repositories.tasks.mappers

import com.android.template.data.remote.models.responses.tasks.TaskResponse
import com.android.template.domain.models.tasks.Task
import io.kotest.matchers.shouldBe
import org.junit.Test

internal class TaskMappersKtTest {

    @Test
    fun `map TaskResponse to Task`() {
        val taskResponse = TaskResponse(id = "123", content = "Test Name")
        val expectedTask = Task(id = "123", content = "Test Name")

        val actualModel = taskResponse.toTask()

        actualModel shouldBe expectedTask
    }

    @Test
    fun `map null TaskResponse to Task`() {
        val taskResponse = TaskResponse()
        val expectedTask = Task()

        val actualModel = taskResponse.toTask()

        actualModel shouldBe expectedTask
    }
}