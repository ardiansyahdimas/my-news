package com.idstar.test.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_articles", indices = [androidx.room.Index(value = ["url"], unique = true)])
data class NewsArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var uId : Int = 0,

    @ColumnInfo(name ="source_id")
    var sourceId: String?,

    @ColumnInfo(name ="source_name")
    var source_name: String?,

    @ColumnInfo(name ="publishedAt")
    var publishedAt: String?,

    @ColumnInfo(name ="author")
    var author: String?,

    @ColumnInfo(name ="urlToImage")
    var urlToImage: String?,

    @ColumnInfo(name ="description")
    var description: String?,

    @ColumnInfo(name ="title")
    var title: String?,

    @ColumnInfo(name ="url")
    var url: String?,

    @ColumnInfo(name ="content")
    var content: String?
)