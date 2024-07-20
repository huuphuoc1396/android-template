package com.android.template.data.remote.services

import com.android.template.data.remote.models.responses.tasks.TaskResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface TasksService {

    @GET("/api/tasks")
    suspend fun getTaskList(): List<TaskResponse>

    @GET("/api/tasks/{id}")
    suspend fun getTask(@Path("id") id: String): TaskResponse

    @POST("/api/tasks")
    suspend fun createTask(): TaskResponse
}