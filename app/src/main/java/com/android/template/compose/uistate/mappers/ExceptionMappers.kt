package com.android.template.compose.uistate.mappers

import android.content.Context
import com.android.template.R
import com.android.template.compose.uistate.models.ErrorState
import com.android.template.domain.models.errors.ApiException
import com.android.template.domain.models.errors.NoConnectionException
import com.android.template.domain.models.errors.UnauthorizedException

fun Throwable?.toErrorState(): ErrorState {
    return when (this) {
        is ApiException -> ErrorState(message = message)
        is NoConnectionException -> ErrorState(messageRes = R.string.error_message_no_internet_connection)
        is UnauthorizedException -> ErrorState(messageRes = R.string.error_message_unauthorized)
        else -> ErrorState(messageRes = R.string.error_message_generic)
    }
}

fun ErrorState.toString(context: Context): String =
    message.ifEmpty { context.getString(messageRes) }