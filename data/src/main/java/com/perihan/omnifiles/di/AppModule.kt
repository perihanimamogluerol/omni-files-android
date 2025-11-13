package com.perihan.omnifiles.di

import com.perihan.omnifiles.local.FolderLocalDataSource
import com.perihan.omnifiles.local.FolderLocalDataSourceImpl
import com.perihan.omnifiles.repository.FolderRepository
import com.perihan.omnifiles.repository.FolderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindFolderLocalDataSource(localDataSource: FolderLocalDataSourceImpl): FolderLocalDataSource

    @Binds
    @Singleton
    abstract fun bindFolderRepository(repository: FolderRepositoryImpl): FolderRepository

    companion object {
        @Provides
        @Singleton
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}