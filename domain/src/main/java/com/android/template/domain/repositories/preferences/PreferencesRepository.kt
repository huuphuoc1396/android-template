package com.android.template.domain.repositories.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    fun isFirstRun(): Flow<Boolean>

    suspend fun setFirstRun(isFirstRun: Boolean)
}