package com.android.template.domain.usecases.preferences

import com.android.template.domain.repositories.preferences.PreferencesRepository
import javax.inject.Inject

class SetFirstRunUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    suspend operator fun invoke(isFirstRun: Boolean) = preferencesRepository.setFirstRun(isFirstRun)
}