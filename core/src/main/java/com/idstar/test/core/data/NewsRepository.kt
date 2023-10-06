package com.idstar.test.core.data

import com.idstar.test.core.NetworkBoundResource
import com.idstar.test.core.data.source.local.LocalDataSource
import com.idstar.test.core.data.source.remote.RemoteDataSource
import com.idstar.test.core.data.source.remote.network.ApiResponse
import com.idstar.test.core.data.source.remote.response.ArticlesItem
import com.idstar.test.core.data.source.remote.response.SourcesNewsResourceItem
import com.idstar.test.core.domain.model.NewsArticleModel
import com.idstar.test.core.domain.model.NewsResourceModel
import com.idstar.test.core.domain.repository.INewsRepository
import com.idstar.test.core.utils.mapper.NewsArticleDataMapper
import com.idstar.test.core.utils.mapper.NewsResourceDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : INewsRepository {
    override fun getNewsResourceByCategory(category:String): Flow<Resource<List<NewsResourceModel>>> =
        object : NetworkBoundResource<List<NewsResourceModel>, List<SourcesNewsResourceItem>>() {
            override fun loadFromDB(): Flow<List<NewsResourceModel>> {
                return localDataSource.getNewsResourceByCategory(category).map { NewsResourceDataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<NewsResourceModel>?): Boolean =
//                data.isNullOrEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<SourcesNewsResourceItem>>> = remoteDataSource.getNewsResourceByCategory(category)


            override suspend fun saveCallResult(data: List<SourcesNewsResourceItem>) {
                val newsResourceList = NewsResourceDataMapper.mapResponsesToEntities(data)
                localDataSource.insertNewsResource(newsResourceList)
            }
        }.asFlow()

    override fun getNewsResources(): Flow<Resource<List<NewsResourceModel>>> =
        object : NetworkBoundResource<List<NewsResourceModel>, List<SourcesNewsResourceItem>>() {
            override fun loadFromDB(): Flow<List<NewsResourceModel>> {
                return localDataSource.getNewsResources().map { NewsResourceDataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<NewsResourceModel>?): Boolean =
//                data.isNullOrEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<SourcesNewsResourceItem>>> = remoteDataSource.getNewsResources()


            override suspend fun saveCallResult(data: List<SourcesNewsResourceItem>) {
                val newsResourceList = NewsResourceDataMapper.mapResponsesToEntities(data)
                localDataSource.insertNewsResource(newsResourceList)
            }
        }.asFlow()

    override fun getNewsArticlesBySourceId(sourceId:String, page:Int): Flow<Resource<List<NewsArticleModel>>> =
        object : NetworkBoundResource<List<NewsArticleModel>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<NewsArticleModel>> {
                return localDataSource.getNewsArticlesBySourceId(sourceId).map { NewsArticleDataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<NewsArticleModel>?): Boolean =
//                data.isNullOrEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> = remoteDataSource.getNewsArticlesBySourceId(sourceId,page)


            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsArticleList = NewsArticleDataMapper.mapResponsesToEntities(data)
                localDataSource.insertNewsArticles(newsArticleList)
            }
        }.asFlow()
}