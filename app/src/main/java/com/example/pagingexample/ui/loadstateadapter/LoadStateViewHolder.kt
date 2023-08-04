package com.example.pagingexample.ui.loadstateadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.databinding.ItemLoadStateBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {

        binding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    companion object {
        fun create(parent: ViewGroup): LoadStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemLoadStateBinding.inflate(layoutInflater, parent, false)
            return LoadStateViewHolder(binding = binding)
        }
    }
}