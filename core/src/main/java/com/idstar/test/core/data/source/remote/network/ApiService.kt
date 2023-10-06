package com.idstar.test.core.data.source.remote.network


import com.idstar.test.core.data.source.remote.response.GetNewsArticlesResponse
import com.idstar.test.core.data.source.remote.response.GetNewsResourceResponse
import retrofit2.http.*


interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("sources")
    suspend fun getNewsResourceByCategory(
        @Query("category") category: String,
    ): GetNewsResourceResponse

    @Headers("Content-Type: application/json")
    @GET("sources")
    suspend fun getNewsResources(): GetNewsResourceResponse

    @Headers("Content-Type: application/json")
    @GET("everything")
    suspend fun getNewsArticlesBySourceId(
        @Query("sources") sources: String,
        @Query("page") page: Int
    ): GetNewsArticlesResponse
}
