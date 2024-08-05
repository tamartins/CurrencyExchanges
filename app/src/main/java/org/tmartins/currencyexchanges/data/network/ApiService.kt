package org.tmartins.currencyexchanges.data.network

import org.tmartins.currencyexchanges.data.network.model.LatestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining API service endpoints.
 */
interface ApiService {

    @GET("/latest")
    suspend fun getLatest(
        @Query("amount") amount: Double? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
    ): Response<LatestResponse?>?

    @GET("/currencies")
    suspend fun getCurrencies(): Response<Map<String, String>?>?
}