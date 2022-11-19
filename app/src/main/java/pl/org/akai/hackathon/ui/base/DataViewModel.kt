package pl.org.akai.hackathon.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class DataViewModel<T>(
	default: T,
) : BaseViewModel() {

	val data = MutableLiveData(default)
	val isLoading = MutableLiveData(true)
	val isError = MutableLiveData(false)
	val isLoaded = MutableLiveData(false)
	val error = MutableLiveData<Throwable>(null)

	protected abstract suspend fun loadDataImpl(): T

	suspend fun loadData() {
		isLoading.postValue(true)
		isError.postValue(false)
		isLoaded.postValue(false)
		try {
			data.postValue(loadDataImpl()!!) // it just works
			isLoaded.postValue(true)
		} catch (e: Exception) {
			error.postValue(e)
			isError.postValue(true)
		}
		isLoading.postValue(false)
	}

	fun refresh() = viewModelScope.launch(Dispatchers.IO) {
		loadData()
	}
}
