package com.idstar.test.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idstar.test.core.data.source.local.entity.NewsResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsResourceDao {

    @Query("SELECT * FROM news_resource")
    fun getNewsResources(): Flow<List<NewsResourceEntity>>

    @Query("SELECT * FROM news_resource where category=:category")
    fun getNewsResourceByCategory(category: String): Flow<List<NewsResourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsResource(categoryList: List<NewsResourceEntity>)

}