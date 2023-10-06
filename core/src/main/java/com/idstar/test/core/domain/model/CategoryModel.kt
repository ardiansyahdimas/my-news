package com.idstar.test.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val id: Int,
    val name:Int,
    val img:Int,
) : Parcelable