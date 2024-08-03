package org.tmartins.currencyexchanges.data.network

import org.tmartins.currencyexchanges.data.network.model.LatestResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface defining API service endpoints.
 */
interface ApiService {

    @GET("/latest")
    suspend fun getLatest(): Response<LatestResponse?>?
}