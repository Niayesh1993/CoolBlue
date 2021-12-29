package xyz.zohre.data.model

import com.squareup.moshi.Json

data class ReviewSummary (

    @Json(name = "reviewAverage") val reviewAverage : Double,
    @Json(name = "reviewCount") val reviewCount : Int
)
