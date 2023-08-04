package com.example.pagingexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.databinding.ActivityMainBinding
import com.example.pagingexample.ui.adapter.QuotesAdapter
import com.example.pagingexample.ui.loadstateadapter.FooterLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter: QuotesAdapter by lazy {
        QuotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        collectData()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvQuotes
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter.withLoadStateFooter(
            FooterLoadStateAdapter()
        )

        binding.apply {
            fabScrollTop.setOnClickListener {
                recyclerView.smoothScrollToPosition(0)
            }

            recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val firstItem = layoutManager.findFirstVisibleItemPosition()
                    fabScrollTop.isVisible = firstItem > 0
                }
            })
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect {
                        adapter.submitData(it)
                    }
                }

                launch {
                    adapter.loadStateFlow.collectLatest { state ->
                        binding.progressBar.isVisible = state.refresh is LoadState.Loading
                    }
                }
            }
        }
    }
}
