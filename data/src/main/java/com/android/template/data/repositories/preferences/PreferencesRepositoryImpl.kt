package com.android.template.data.repositories.preferences

import com.android.template.data.storages.datastore.preferences.PreferencesDataStore
import com.android.template.domain.repositories.preferences.PreferencesRepository
import javax.inject.Inject

internal class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
) : PreferencesRepository {

    override fun isFirstRun() = preferencesDataStore.isFirstRun()

    override suspend fun setFirstRun(isFirstRun: Boolean) =
        preferencesDataStore.setFirstRun(isFirstRun)
}