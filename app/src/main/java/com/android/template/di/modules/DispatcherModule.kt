package com.android.template.di.modules

import com.android.template.providers.dispatchers.DispatcherProvider
import com.android.template.providers.dispatchers.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DispatcherModule {

    @Binds
    @Singleton
    fun bindDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider
}