package pl.org.akai.hackathon.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class DataListViewModel<
	T : Any,
	B : ViewBinding,
	D : DataListViewModel<T, B, D>,
>(
	private val pagingSourceFactory: () -> PagingSource<Int, T>,
	private val adapterFactory: (vm: D) -> PagingDataAdapter<T, BindingViewHolder<B>>,
) : BaseViewModel() {

	val isLoading = MutableLiveData(true)
	val isError = MutableLiveData(false)
	val isLoaded = MutableLiveData(false)
	val error = MutableLiveData<Throwable>(null)

	private var pagingSource: PagingSource<Int, T>? = null
	val flow = Pager(
		PagingConfig(pageSize = 10, initialLoadSize = 20),
	) {
		pagingSource = pagingSourceFactory()
		pagingSource!!
	}.flow.cachedIn(viewModelScope)

	private val adapterBase by lazy { adapterFactory(this as D) }
	val adapter
		get() = adapterBase.withLoadStateHeaderAndFooter(
			header = ListLoadStateAdapter(adapterBase::retry),
			footer = ListLoadStateAdapter(adapterBase::retry),
		)

	fun loadData() {
		isLoading.postValue(true)
		isError.postValue(false)
		isLoaded.postValue(false)
		viewModelScope.launch {
			adapterBase.loadStateFlow.collectLatest { loadStates ->
				if (isLoaded.value == true)
					return@collectLatest
				isLoading.postValue(loadStates.refresh is LoadState.Loading)
				isError.postValue(loadStates.refresh is LoadState.Error)
				isLoaded.postValue(loadStates.refresh is LoadState.NotLoading)
			}
		}
		viewModelScope.launch {
			flow.collectLatest { pagingData ->
				adapterBase.submitData(pagingData)
			}
		}
	}

	fun refresh() = viewModelScope.launch(Dispatchers.IO) {
		pagingSource?.invalidate()
	}
}

