package org.tmartins.currencyexchanges.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.tmartins.currencyexchanges.data.database.AppDatabase
import org.tmartins.currencyexchanges.data.datasource.ExchangeLocalDataSource
import org.tmartins.currencyexchanges.data.datasource.ExchangeRemoteDataSource
import org.tmartins.currencyexchanges.data.network.ApiService
import org.tmartins.currencyexchanges.data.repository.ExchangeRepositoryImpl
import org.tmartins.currencyexchanges.domain.interfaces.ConvertCurrencyUseCase
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.interfaces.GetCurrenciesUseCase
import org.tmartins.currencyexchanges.domain.interfaces.GetLatestRatesUseCase
import org.tmartins.currencyexchanges.domain.interfaces.UpdateCurrenciesUseCase
import org.tmartins.currencyexchanges.domain.usecase.ConvertCurrencyUseCaseImpl
import org.tmartins.currencyexchanges.domain.usecase.GetCurrenciesUseCaseImpl
import org.tmartins.currencyexchanges.domain.usecase.GetLatestRatesUseCaseImpl
import org.tmartins.currencyexchanges.domain.usecase.UpdateCurrenciesUseCaseImpl
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
    fun provideAppDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun providesExchangeRemoteDataSource(apiServices: ApiService): ExchangeRemoteDataSource =
        ExchangeRemoteDataSource(apiServices)

    @Provides
    @Singleton
    fun providesExchangeLocalDataSource(appDatabase: AppDatabase): ExchangeLocalDataSource =
        ExchangeLocalDataSource(appDatabase)

    @Provides
    @Singleton
    fun providesExchangeRepository(
        exchangeRemoteDataSource: ExchangeRemoteDataSource,
        exchangeLocalDataSource: ExchangeLocalDataSource,
    ): ExchangeRepository =
        ExchangeRepositoryImpl(exchangeRemoteDataSource, exchangeLocalDataSource)

    @Provides
    fun providesGetLatestRatesUseCase(exchangeRepository: ExchangeRepository): GetLatestRatesUseCase =
        GetLatestRatesUseCaseImpl(exchangeRepository)

    @Provides
    fun providesConvertCurrencyUseCase(exchangeRepository: ExchangeRepository): ConvertCurrencyUseCase =
        ConvertCurrencyUseCaseImpl(exchangeRepository)

    @Provides
    fun providesGetCurrenciesUseCase(exchangeRepository: ExchangeRepository): GetCurrenciesUseCase =
        GetCurrenciesUseCaseImpl(exchangeRepository)

    @Provides
    fun providesUpdateCurrenciesUseCase(exchangeRepository: ExchangeRepository): UpdateCurrenciesUseCase =
        UpdateCurrenciesUseCaseImpl(exchangeRepository)
}