package com.android.template.compose.uistate.models

import androidx.annotation.StringRes

data class ErrorState(
    val message: String = "",
    @StringRes
    val messageRes: Int = -1,
) {
    fun hasError() = message.isNotEmpty() || messageRes != -1
}