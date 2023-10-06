package com.idstar.test.core.utils.mapper

import com.idstar.test.core.data.source.local.entity.NewsResourceEntity
import com.idstar.test.core.data.source.remote.response.SourcesNewsResourceItem
import com.idstar.test.core.domain.model.NewsResourceModel

object NewsResourceDataMapper {
    fun mapResponsesToEntities(input: List<SourcesNewsResourceItem>): List<NewsResourceEntity> {
        val newsResourceList = ArrayList<NewsResourceEntity>()
        input.map {
            val newsResource = NewsResourceEntity(
                id = it.id.toString(),
                name = it.name,
                description = it.description,
                url = it.url,
                category = it.category,
                language = it.language,
                country = it.country
            )
            newsResourceList.add(newsResource)
        }
        return newsResourceList
    }

    fun mapEntitiesToDomain(input: List<NewsResourceEntity>): List<NewsResourceModel> =
        input.map {
            NewsResourceModel(
                id = it.id,
                name = it.name,
                description = it.description,
                url = it.url,
                category = it.category,
                language = it.language,
                country = it.country
            )
        }
}