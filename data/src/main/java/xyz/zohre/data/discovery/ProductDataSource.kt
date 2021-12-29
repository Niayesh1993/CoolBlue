package xyz.zohre.data.discovery

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.zohre.data.model.CoolBlueResponse

interface ProductDataSource {

    companion object {
        private const val QUE = 1
    }

    @GET("mobile-assignment/search")
    suspend fun searchProduct(
        @Query("que") limit: Int = QUE
    ): Response<CoolBlueResponse>

}