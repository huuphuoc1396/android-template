package com.android.template.data.remote.models.responses.tasks

import com.google.gson.annotations.SerializedName

internal data class TaskResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("content")
    val content: String? = null,
)