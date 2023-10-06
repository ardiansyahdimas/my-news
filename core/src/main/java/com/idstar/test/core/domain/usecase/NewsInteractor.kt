package com.idstar.test.core.domain.usecase

import com.idstar.test.core.data.Resource
import com.idstar.test.core.domain.model.NewsArticleModel
import com.idstar.test.core.domain.model.NewsResourceModel
import com.idstar.test.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository):
    NewsUseCase {
    override fun getNewsResources(): Flow<Resource<List<NewsResourceModel>>> = newsRepository.getNewsResources()

    override fun getNewsResourceByCategory(category: String): Flow<Resource<List<NewsResourceModel>>> = newsRepository.getNewsResourceByCategory(category)

    override fun getNewsArticlesBySourceId(
        sourceId: String,
        page: Int
    ): Flow<Resource<List<NewsArticleModel>>>  = newsRepository.getNewsArticlesBySourceId(sourceId,page)
}