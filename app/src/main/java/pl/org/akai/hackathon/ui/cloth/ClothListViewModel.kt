package pl.org.akai.hackathon.ui.cloth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.insertFooterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Cloth
import pl.org.akai.hackathon.databinding.ClothGridItemBinding
import pl.org.akai.hackathon.ui.base.DataListViewModel
import javax.inject.Inject

@HiltViewModel
class ClothListViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataListViewModel<Cloth, ClothGridItemBinding, ClothListViewModel>(
	pagingSourceFactory = { ClothListPagingSource(apiService, it.query.value ?: ClothListQuery(), it) },
	adapterFactory = { ClothListAdapter(ClothListComparator, it) },
) {

	var query = MutableLiveData(ClothListQuery())

	var amountSaved = MutableLiveData(0)
	var amountWasted = MutableLiveData(0)
	var unusedCount = MutableLiveData(0)
	var count = MutableLiveData(0)

	private val _clothClicked = MutableLiveData<Cloth?>(null)
	val clothClicked: LiveData<Cloth?>
		get() = _clothClicked

	fun onItemClick(item: Cloth) {
		_clothClicked.value = item
	}

	fun endClothClicked() {
		_clothClicked.value = null
	}

	override fun loadData() {
		super.loadData()
		viewModelScope.launch(Dispatchers.IO) {
			reloadStats()
		}
	}

	private suspend fun reloadStats() {
		val list = apiService.getUnavailableClothes()
		amountSaved.postValue(list.filter { it.status == "sold" }.sumOf { it.category.savings })
		amountWasted.postValue(list.filter { it.status == "thrown" }.sumOf { it.category.savings })
	}

	fun updateQuery() {
		query.postValue(query.value)
		pagingSource?.invalidate()
	}

	fun showUnused() {
		query.value?.unused = true
		updateQuery()
	}

	fun sellCloth(cloth: Cloth) = viewModelScope.launch(Dispatchers.IO) {
		apiService.sellCloth(cloth.id)
		pagingSource?.invalidate()
		reloadStats()
	}

	fun throwCloth(cloth: Cloth) = viewModelScope.launch(Dispatchers.IO) {
		apiService.throwCloth(cloth.id)
		pagingSource?.invalidate()
		reloadStats()
	}
}
