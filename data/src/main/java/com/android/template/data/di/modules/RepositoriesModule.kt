package com.android.template.data.di.modules

import com.android.template.data.repositories.preferences.PreferencesRepositoryImpl
import com.android.template.data.repositories.tasks.TasksRepositoryImpl
import com.android.template.domain.repositories.preferences.PreferencesRepository
import com.android.template.domain.repositories.tasks.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoriesModule {

    @Binds
    fun bindTasksRepository(impl: TasksRepositoryImpl): TasksRepository

    @Binds
    fun providePreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository
}