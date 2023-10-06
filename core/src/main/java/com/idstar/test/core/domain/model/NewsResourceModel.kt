package com.idstar.test.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResourceModel(
    val country: String? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val id: String? = null,
    val category: String? = null,
    val url: String? = null,
    var expandible : Boolean? = false,
) : Parcelable


