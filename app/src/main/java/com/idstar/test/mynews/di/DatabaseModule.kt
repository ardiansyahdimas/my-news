package com.idstar.test.mynews.di

import android.content.Context
import androidx.room.Room
import com.idstar.test.core.data.source.local.room.NewsArticleDao
import com.idstar.test.core.data.source.local.room.NewsDatabase
import com.idstar.test.core.data.source.local.room.NewsResourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context): NewsDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("news".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java, "newsapp.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideNewsResourceDao(database: NewsDatabase): NewsResourceDao = database.newsResourceDao()

    @Provides
    fun provideNewsArticleDao(database: NewsDatabase): NewsArticleDao = database.newsArticlesDao()
}