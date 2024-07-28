package com.android.template.domain.utils

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0L

fun Float?.orZero() = this ?: 0f

fun Double?.orZero() = this ?: 0.0

fun String?.orEmpty() = this ?: ""

fun <T> List<T>?.orEmpty() = this ?: emptyList()

fun <K, V> Map<K, V>?.orEmpty() = this ?: emptyMap()