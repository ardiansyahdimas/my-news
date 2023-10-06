package com.idstar.test.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArticleModel(
    var id: Int?,
    var sourceId: String?,
    var source_name: String?,
    var publishedAt: String?,
    var author: String?,
    var urlToImage: String?,
    var description: String?,
    var title: String?,
    var url: String?,
    var content: String?
):Parcelable