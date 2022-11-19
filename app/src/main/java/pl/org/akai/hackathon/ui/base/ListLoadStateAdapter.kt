package pl.org.akai.hackathon.ui.base

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ListLoadStateAdapter(
	private val onRetry: () -> Unit
) : LoadStateAdapter<ListLoadStateViewHolder>() {

	override fun onCreateViewHolder(
		parent: ViewGroup,
		loadState: LoadState
	) = ListLoadStateViewHolder(parent, onRetry)

	override fun onBindViewHolder(
		holder: ListLoadStateViewHolder,
		loadState: LoadState
	) = holder.bind(loadState)
}

