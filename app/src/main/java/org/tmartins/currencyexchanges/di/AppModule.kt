package org.tmartins.currencyexchanges.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

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