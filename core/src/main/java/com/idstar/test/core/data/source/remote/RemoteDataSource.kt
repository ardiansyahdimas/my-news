package com.idstar.test.core.data.source.remote

import com.idstar.test.core.data.source.remote.network.ApiResponse
import com.idstar.test.core.data.source.remote.network.ApiService
import com.idstar.test.core.data.source.remote.response.ArticlesItem
import com.idstar.test.core.data.source.remote.response.SourcesNewsResourceItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    val TAG = "RemoteDataSource"
    suspend fun getNewsResourceByCategory(category:String): Flow<ApiResponse<List<SourcesNewsResourceItem>>> {
        return flow {
            try {
                val response = apiService.getNewsResourceByCategory(category)
                val dataArray = response.sources
                if(dataArray?.isNotEmpty() == true){
                    emit(ApiResponse.Success(response.sources))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.tag(TAG).d("getNewsResourceByCategory: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNewsResources(): Flow<ApiResponse<List<SourcesNewsResourceItem>>> {
        return flow {
            try {
                val response = apiService.getNewsResources()
                val dataArray = response.sources
                if(dataArray?.isNotEmpty() == true){
                    emit(ApiResponse.Success(response.sources))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.tag(TAG).d("getNewsResourceByCategory: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNewsArticlesBySourceId(sourceId:String, page:Int): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = apiService.getNewsArticlesBySourceId(sourceId, page)
                val dataArray = response.articles
                if(dataArray?.isNotEmpty() == true){
                    emit(ApiResponse.Success(response.articles))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Timber.tag(TAG).d("getNewsArticlesBySourceId: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }
}
