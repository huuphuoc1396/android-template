package com.android.template.compose.uistate.models

data class ErrorState(
    val message: String? = null,
) {
    fun hasError() = message != null
}