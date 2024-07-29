package com.android.template.domain.models.errors

data class UnauthorizedException(
    override val message: String = "Unauthorized",
) : RuntimeException()