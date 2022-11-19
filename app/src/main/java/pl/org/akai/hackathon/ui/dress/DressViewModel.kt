package pl.org.akai.hackathon.ui.dress

import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class DressViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataViewModel<List<DressModel>>(mutableListOf()) {

	lateinit var originalData: List<DressModel>

	override suspend fun loadDataImpl(): List<DressModel> {
		originalData = apiService.getClothes(1, 1).map { DressModel(it) }.sort()
		return originalData
	}

	fun onClothSelected(item: DressModel) {
		val items = originalData
		// hide all items in this category
		items.onEach { if (it.cloth.category == item.cloth.category) it.state = DressModel.State.HIDDEN }
		// ..except the selected item
		item.state = DressModel.State.SELECTED
		postList(items)
	}

	fun onClothUnselected(item: DressModel) {
		val items = originalData
		// show all items in this category
		items.onEach { if (it.cloth.category == item.cloth.category) it.state = DressModel.State.UNSELECTED }
		postList(items)
	}

	private fun postList(items: List<DressModel>) {
		data.postValue(items.sort())
	}

	private fun List<DressModel>.sort() = this
		.sortedBy { it.cloth.name }
		.sortedBy { it.cloth.category.name }
		.sortedBy { it.state.ordinal }
		.filter { it.state != DressModel.State.HIDDEN }
}
