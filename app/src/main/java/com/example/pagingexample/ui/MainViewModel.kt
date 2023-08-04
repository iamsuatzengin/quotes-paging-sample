package com.example.pagingexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingexample.data.model.Quote
import com.example.pagingexample.data.paging.QuotesPagingSourceFlow
import com.example.pagingexample.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<Quote>>(PagingData.empty())
    val state = _state.asStateFlow()

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            val pager = Pager(
                PagingConfig(
                    pageSize = 20, initialLoadSize = 20
                )
            ) {
//                QuotesPagingSource(apiService)
                QuotesPagingSourceFlow(apiService)
            }

            pager.flow.cachedIn(viewModelScope).collect { pagingData ->
                _state.emit(pagingData)
            }
        }
    }
}