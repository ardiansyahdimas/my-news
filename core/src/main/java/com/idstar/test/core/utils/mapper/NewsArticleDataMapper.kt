package com.idstar.test.core.utils.mapper

import com.idstar.test.core.data.source.local.entity.NewsArticleEntity
import com.idstar.test.core.data.source.remote.response.ArticlesItem
import com.idstar.test.core.domain.model.NewsArticleModel

object NewsArticleDataMapper {
    fun mapResponsesToEntities(input: List<ArticlesItem>): List<NewsArticleEntity> {
        val newsArticleList = ArrayList<NewsArticleEntity>()
        input.map {
            val newsArticle = NewsArticleEntity(
                sourceId = it.source?.id,
                source_name = it.source?.name,
                publishedAt = it.publishedAt,
                author = it.author,
                urlToImage  = it.urlToImage,
                description = it.description,
                title = it.title,
                url = it.url,
                content = it.content
            )
            newsArticleList.add(newsArticle)
        }
        return newsArticleList
    }

    fun mapEntitiesToDomain(input: List<NewsArticleEntity>): List<NewsArticleModel> =
        input.map {
            NewsArticleModel(
                id = it.uId,
                sourceId = it.sourceId,
                source_name = it.source_name,
                publishedAt = it.publishedAt,
                author = it.author,
                urlToImage  = it.urlToImage,
                description = it.description,
                title = it.title,
                url = it.url,
                content = it.content
            )
        }
}