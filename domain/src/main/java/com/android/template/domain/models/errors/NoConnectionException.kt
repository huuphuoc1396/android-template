package com.android.template.domain.models.errors

data class NoConnectionException(
    override val message: String = "No connection",
) : Exception()