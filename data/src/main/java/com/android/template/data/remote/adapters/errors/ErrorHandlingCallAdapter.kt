package com.android.template.data.remote.adapters.errors

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import java.lang.reflect.Type

internal class ErrorHandlingCallAdapter<R>(
    private val responseType: Type,
) : CallAdapter<R, Call<R>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<R> = MappingApiErrorCall(call)

    private class MappingApiErrorCall<R>(
        private val delegate: Call<R>,
    ) : Call<R> by delegate {
        override fun enqueue(callback: Callback<R>) {
            delegate.enqueue(ErrorHandlingCallback(callback))
        }

        override fun clone(): Call<R> = MappingApiErrorCall(delegate.clone())
    }
}