package com.idstar.test.core.data.source.local


import com.idstar.test.core.data.source.local.entity.NewsArticleEntity
import com.idstar.test.core.data.source.local.entity.NewsResourceEntity
import com.idstar.test.core.data.source.local.room.NewsArticleDao
import com.idstar.test.core.data.source.local.room.NewsResourceDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val newsResourceDao: NewsResourceDao,
    private val newsArticleDao: NewsArticleDao
) {
//    NEWS RESOURCE
    fun getNewsResources():Flow<List<NewsResourceEntity>> = newsResourceDao.getNewsResources()
    fun getNewsResourceByCategory(category: String):Flow<List<NewsResourceEntity>> = newsResourceDao.getNewsResourceByCategory(category)
    suspend fun insertNewsResource(newsResourceList: List<NewsResourceEntity>) = newsResourceDao.insertNewsResource(newsResourceList)

//    NEWS ARTICLES
    fun getNewsArticlesBySourceId(sourceId: String):Flow<List<NewsArticleEntity>> = newsArticleDao.getNewsArticlesBySourceId(sourceId)
    suspend fun insertNewsArticles(newsArticleList: List<NewsArticleEntity>) = newsArticleDao.insertNewsArticles(newsArticleList)
}