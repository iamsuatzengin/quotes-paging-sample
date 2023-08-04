package com.example.pagingexample.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("totalCount")
    val totalCount: Int? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("totalPages")
    val totalPages: Int? = null,
    @SerialName("lastItemIndex")
    val lastItemIndex: Int? = null,
    @SerialName("results")
    val quotes: List<Quote>? = null
)

