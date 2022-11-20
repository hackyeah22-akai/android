package pl.org.akai.hackathon.ui.cloth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Cloth
import pl.org.akai.hackathon.databinding.ClothGridItemBinding
import pl.org.akai.hackathon.ui.base.DataListViewModel
import javax.inject.Inject

@HiltViewModel
class ClothListViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataListViewModel<Cloth, ClothGridItemBinding, ClothListViewModel>(
	pagingSourceFactory = { ClothListPagingSource(apiService, it.query) },
	adapterFactory = { ClothListAdapter(ClothListComparator, it) },
) {

	var query = ClothListQuery()

	private val _clothClicked = MutableLiveData<Cloth?>(null)
	val clothClicked: LiveData<Cloth?>
		get() = _clothClicked

	fun onItemClick(item: Cloth) {
		_clothClicked.value = item
	}

	fun endClothClicked() {
		_clothClicked.value = null
	}

	fun updateQuery() {
		pagingSource?.invalidate()
	}
}
