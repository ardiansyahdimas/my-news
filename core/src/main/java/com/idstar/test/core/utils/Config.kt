package com.idstar.test.core.utils

import com.idstar.test.core.R
import com.idstar.test.core.domain.model.CategoryModel

object Config {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val TOKEN  = "efda12458a9e4408ba7e18c79eb0be07"

    val dummyCategory = listOf(
        CategoryModel(
            0,
            R.string.category_business,
            R.drawable.img_business
        ),
        CategoryModel(
            1,
            R.string.category_entertaiment,
            R.drawable.img_entertainment
        ),
        CategoryModel(
            2,
            R.string.category_general,
            R.drawable.img_general
        ),
        CategoryModel(
            3,
            R.string.category_health,
            R.drawable.img_health
        ),
        CategoryModel(
            4,
            R.string.category_science,
            R.drawable.img_science
        ),
        CategoryModel(
            5,
            R.string.category_sports,
            R.drawable.img_sports
        ),
        CategoryModel(
                6,
        R.string.category_technology,
        R.drawable.img_technology
        ),
        CategoryModel(
            7,
            R.string.category_all,
            R.drawable.img_all
        )
    )
}