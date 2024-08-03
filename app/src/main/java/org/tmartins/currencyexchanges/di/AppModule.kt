package org.tmartins.currencyexchanges.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.tmartins.currencyexchanges.data.datasource.ExchangeRemoteDataSource
import org.tmartins.currencyexchanges.data.network.ApiService
import org.tmartins.currencyexchanges.data.repository.ExchangeRepositoryImpl
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.interfaces.GetLatestRatesUseCase
import org.tmartins.currencyexchanges.domain.usecase.GetLatestRatesUseCaseImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://www.frankfurter.app"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val client: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(): ApiService = client.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesExchangeRemoteDataSource(apiServices: ApiService): ExchangeRemoteDataSource =
        ExchangeRemoteDataSource(apiServices)

    @Provides
    @Singleton
    fun providesExchangeRepository(exchangeRemoteDataSource: ExchangeRemoteDataSource): ExchangeRepository =
        ExchangeRepositoryImpl(exchangeRemoteDataSource)

    @Provides
    fun providesGetLatestRatesUseCase(exchangeRepository: ExchangeRepository): GetLatestRatesUseCase =
        GetLatestRatesUseCaseImpl(exchangeRepository)

}