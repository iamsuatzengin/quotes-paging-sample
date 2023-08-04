package com.example.pagingexample.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pagingexample.data.model.Quote

class QuotesAdapter : PagingDataAdapter<Quote, QuotesViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
