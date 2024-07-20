package com.android.template.extenstions.errors

import com.android.template.compose.uistate.models.ErrorState

fun Throwable.toErrorState(): ErrorState {
    return ErrorState(message = this.localizedMessage ?: "")
}