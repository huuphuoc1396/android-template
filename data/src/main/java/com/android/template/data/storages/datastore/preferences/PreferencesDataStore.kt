package com.android.template.data.storages.datastore.preferences

import kotlinx.coroutines.flow.Flow

internal interface PreferencesDataStore {

    fun isFirstRun(): Flow<Boolean>

    suspend fun setFirstRun(isFirstRun: Boolean)
}