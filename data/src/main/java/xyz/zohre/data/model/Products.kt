package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Products (

    @Json(name = "productId") val productId : Int,
    @Json(name = "productName") val productName : String,
    @Json(name = "reviewInformation") val reviewInformation : ReviewInformation,
    @Json(name = "USPs") val uSPs : List<String>,
    @Json(name = "availabilityState") val availabilityState : Int,
    @Json(name = "salesPriceIncVat") val salesPriceIncVat : Double,
    @Json(name = "productImage") val productImage : String,
    @Json(name = "nextDayDelivery") val nextDayDelivery : Boolean
)