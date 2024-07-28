package com.android.template.data.remote.adapters.errors

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *> {
        val responseType = getParameterUpperBound(0, returnType as ParameterizedType)
        return ErrorHandlingCallAdapter<Any>(responseType)
    }

    companion object {
        fun create() = ErrorHandlingCallAdapterFactory()
    }
}


