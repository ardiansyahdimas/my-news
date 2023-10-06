package com.idstar.test.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "news_resource", indices = [androidx.room.Index(value = ["id"], unique = true)])
data class NewsResourceEntity(
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "language")
    var language: String?,

    @ColumnInfo(name = "country")
    var country: String?,
)