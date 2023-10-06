package com.idstar.test.mynews.di

import com.idstar.test.core.data.NewsRepository
import com.idstar.test.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideNewsRepository(newsRepository: NewsRepository): INewsRepository
}