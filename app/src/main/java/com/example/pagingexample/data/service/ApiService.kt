package com.example.pagingexample.data.service

import com.example.pagingexample.data.NetworkResult
import com.example.pagingexample.data.model.QuotesResponse
import kotlinx.coroutines.flow.Flow

interface ApiService {

    suspend fun getQuotes(page: Int): QuotesResponse

    suspend fun getQuotesWithFlow(page: Int): Flow<NetworkResult<QuotesResponse>>
}