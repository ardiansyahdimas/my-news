package com.idstar.test.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idstar.test.core.data.source.local.entity.NewsArticleEntity
import com.idstar.test.core.data.source.local.entity.NewsResourceEntity

@Database(entities = [
    NewsResourceEntity::class,
    NewsArticleEntity::class
], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsResourceDao(): NewsResourceDao
    abstract fun newsArticlesDao(): NewsArticleDao
}