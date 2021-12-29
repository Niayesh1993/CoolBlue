package xyz.zohre.data.model

import com.squareup.moshi.Json

data class CoolBlueResponse(
    @Json(name = "products") val products : List<Products>,
    @Json(name = "currentPage") val currentPage : Int,
    @Json(name = "pageSize") val pageSize : Int,
    @Json(name = "totalResults") val totalResults : Int,
    @Json(name = "pageCount") val pageCount : Int
)
