package com.android.template.domain.usecases.preferences

import com.android.template.domain.repositories.preferences.PreferencesRepository
import javax.inject.Inject

class IsFirstRunUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    operator fun invoke() = preferencesRepository.isFirstRun()
}