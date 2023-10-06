package com.idstar.test.mynews.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idstar.test.core.data.Resource
import com.idstar.test.core.domain.model.NewsArticleModel
import com.idstar.test.core.domain.model.NewsResourceModel
import com.idstar.test.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (private val newsUseCase: NewsUseCase) : ViewModel() {
    var resultValueNewsResource: LiveData<Resource<List<NewsResourceModel>>>? = null
    var resultValueNewsArticles: LiveData<Resource<List<NewsArticleModel>>>? = null

    fun getNewsResources() {
       resultValueNewsResource =  newsUseCase.getNewsResources().asLiveData()
    }

    fun getNewsResourceByCategory(category:String) {
        resultValueNewsResource = newsUseCase.getNewsResourceByCategory(category).asLiveData()
    }

    fun getNewsArticlesBySourceId(sourceId:String, page:Int){
        resultValueNewsArticles = newsUseCase.getNewsArticlesBySourceId(sourceId, page).asLiveData()
    }
}