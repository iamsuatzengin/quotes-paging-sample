package com.example.pagingexample.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("_id")
    val id: String ? = null,
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("tags")
    val tags: List<String>? = null,
    @SerialName("authorSlug")
    val authorSlug: String? = null,
    @SerialName("length")
    val length: Int? = null,
    @SerialName("dateAdded")
    val dateAdded: String? = null,
    @SerialName("dateModified")
    val dateModified: String? = null,
)