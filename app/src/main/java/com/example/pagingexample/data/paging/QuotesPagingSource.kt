package com.example.pagingexample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingexample.data.model.Quote
import com.example.pagingexample.data.service.ApiService
import kotlinx.coroutines.delay

class QuotesPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {

        return try {
            val position = params.key ?: 1

            delay(2000)
            val response = apiService.getQuotes(page = position)

            val nextKey = if (position == response.totalPages) {
                null
            } else {
                position.plus(1)
            }

            LoadResult.Page(
                data = response.quotes ?: emptyList(),
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}