package com.example.pagingexample.di

import android.util.Log
import com.example.pagingexample.Constants.BASE_URL
import com.example.pagingexample.data.service.ApiService
import com.example.pagingexample.data.service.ApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        defaultRequest {
            url(BASE_URL)
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("KTOR_LOGGER_TAG", "message : $message")
                }
            }
            level = LogLevel.BODY
        }

        engine {
            connectTimeout = 30_000
            socketTimeout = 30_000
        }
    }

    @Provides
    @Singleton
    fun provideApiService(
        httpClient: HttpClient
    ): ApiService = ApiServiceImpl(httpClient)
}