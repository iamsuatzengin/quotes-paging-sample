package com.example.pagingexample.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pagingexample.data.model.Quote

class DiffCallback : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem == newItem
    }
}
