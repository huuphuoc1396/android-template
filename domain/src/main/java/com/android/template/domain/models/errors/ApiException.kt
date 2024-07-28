package com.android.template.domain.models.errors

data class ApiException(
    val code: Int = 0,
    override val message: String = "",
) : RuntimeException()