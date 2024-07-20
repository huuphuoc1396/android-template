package com.android.template.data.repositories.di

import com.android.template.data.repositories.TasksRepositoryImpl
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
}