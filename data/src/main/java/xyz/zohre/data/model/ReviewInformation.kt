package xyz.zohre.data.model

import com.squareup.moshi.Json

data class ReviewInformation (

    @Json(name = "reviews") val reviews : List<String>,
    @Json(name = "reviewSummary") val reviewSummary : ReviewSummary
)