package pl.org.akai.hackathon.ui.base

import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.paging.LoadState
import pl.org.akai.hackathon.databinding.LoadStateItemBinding

class ListLoadStateViewHolder(
	parent: ViewGroup,
	onRetry: () -> Unit,
) : BindingViewHolder<LoadStateItemBinding>(LoadStateItemBinding::inflate, parent) {

	private val progressBar = b.progressBar
	private val errorText = b.errorText
	private val retryButton = b.retryButton.also {
		it.setOnClickListener { onRetry() }
	}

	fun bind(loadState: LoadState) {
		if (loadState is LoadState.Error) {
			errorText.text = loadState.error.localizedMessage
		}

		progressBar.isInvisible = loadState !is LoadState.Loading
		errorText.isInvisible = loadState !is LoadState.Error
		retryButton.isInvisible = loadState !is LoadState.Error
	}
}

