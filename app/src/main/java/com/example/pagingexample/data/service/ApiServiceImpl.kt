package com.example.pagingexample.data.service

import com.example.pagingexample.data.NetworkResult
import com.example.pagingexample.data.model.QuotesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.http.parameters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : ApiService {
    override suspend fun getQuotes(page: Int): QuotesResponse {
        val response = httpClient.get("quotes") {
            parameters {
                append("page", page.toString())
            }
        }

        return response.body()
    }

    override suspend fun getQuotesWithFlow(page: Int): Flow<NetworkResult<QuotesResponse>> = flow {
        val response = httpClient.get("quotes") {
            parameters {
                append("page", page.toString())
            }
        }

        if (response.status.isSuccess()) {
            emit(NetworkResult.Success(data = response.body()))
        } else {
            emit(NetworkResult.Error(error = response.status.description))
        }
    }
}

