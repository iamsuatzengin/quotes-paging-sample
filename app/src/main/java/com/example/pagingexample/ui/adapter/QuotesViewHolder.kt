package com.example.pagingexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.data.model.Quote
import com.example.pagingexample.databinding.ItemQuotesBinding

class QuotesViewHolder(
    private val binding: ItemQuotesBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Quote?) {
        binding.apply {
            item?.let { quote ->
                tvQuotes.text = quote.content
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): QuotesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemQuotesBinding.inflate(layoutInflater, parent, false)
            return QuotesViewHolder(binding = binding)
        }
    }
}