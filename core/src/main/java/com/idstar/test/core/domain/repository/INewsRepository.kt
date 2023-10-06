package com.idstar.test.core.domain.repository

import com.idstar.test.core.data.Resource
import com.idstar.test.core.domain.model.NewsArticleModel
import com.idstar.test.core.domain.model.NewsResourceModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getNewsResources(): Flow<Resource<List<NewsResourceModel>>>
    fun getNewsResourceByCategory(category:String): Flow<Resource<List<NewsResourceModel>>>
    fun getNewsArticlesBySourceId(sourceId:String, page:Int): Flow<Resource<List<NewsArticleModel>>>
}