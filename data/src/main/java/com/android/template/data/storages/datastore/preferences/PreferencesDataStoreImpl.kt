package com.android.template.data.storages.datastore.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.osipxd.security.crypto.encryptedPreferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val FILE_NAME = "android_template"
private val Context.preferences by encryptedPreferencesDataStore(FILE_NAME)

internal class PreferencesDataStoreImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
) : PreferencesDataStore {

    override fun isFirstRun() = context.preferences.data.map { it[KEY_IS_FIRST] ?: true }

    override suspend fun setFirstRun(isFirstRun: Boolean) {
        context.preferences.edit { preferences -> preferences[KEY_IS_FIRST] = isFirstRun }
    }

    companion object {
        private val KEY_IS_FIRST = booleanPreferencesKey("is_first")
    }
}