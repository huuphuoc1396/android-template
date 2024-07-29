package com.android.template.data.remote.adapters.errors

import com.android.template.data.remote.adapters.mappers.toThrowable
import com.android.template.domain.models.errors.NoConnectionException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InterruptedIOException
import java.net.UnknownHostException

internal class ErrorHandlingCallback<R>(
    private val delegate: Callback<R>,
) : Callback<R> {
    override fun onResponse(call: Call<R>, response: Response<R>) {
        if (response.isSuccessful) {
            delegate.onResponse(call, response)
        } else {
            delegate.onFailure(call, response.toThrowable())
        }
    }

    override fun onFailure(call: Call<R>, throwable: Throwable) {
        when (throwable) {
            is UnknownHostException,
            is InterruptedIOException -> delegate.onFailure(call, NoConnectionException())

            else -> delegate.onFailure(call, throwable)
        }
    }
}