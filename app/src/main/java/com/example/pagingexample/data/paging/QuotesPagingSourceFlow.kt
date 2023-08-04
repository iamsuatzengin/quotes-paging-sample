package com.example.pagingexample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingexample.data.NetworkResult
import com.example.pagingexample.data.model.Quote
import com.example.pagingexample.data.service.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map

class QuotesPagingSourceFlow(
    private val apiService: ApiService
) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        val position = params.key ?: 1

        delay(2000)

        val responseFlow =
            apiService.getQuotesWithFlow(page = position).map { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        val list = result.data.quotes
                        val totalPages = result.data.totalPages

                        val nextKey = if (position == totalPages) {
                            null
                        } else {
                            position.plus(1)
                        }

                        LoadResult.Page(
                            data = list ?: emptyList(),
                            prevKey = if (position == 1) null else position.minus(1),
                            nextKey = nextKey
                        )
                    }

                    is NetworkResult.Error ->
                        LoadResult.Error<Int, Quote>(Throwable(result.error))
                }
            }

        return responseFlow.last()
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}