package pl.org.akai.hackathon.ui.dress

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class DressViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataViewModel<List<DressModel>>(mutableListOf()) {

	lateinit var originalData: List<DressModel>

	val count = MutableLiveData<Int>()

	override suspend fun loadDataImpl(): List<DressModel> {
		originalData = apiService.getClothes(1, 1).map { DressModel(it) }.sort()
		count.postValue(originalData.size)
		return originalData
	}

	fun onClothSelected(item: DressModel) {
		val items = originalData
		// hide all items in this category
		items.onEach {
			if (it.cloth.category == item.cloth.category) it.state = DressModel.State.HIDDEN
		}
		// ..except the selected item
		item.state = DressModel.State.SELECTED
		postList(items)
	}

	fun onClothUnselected(item: DressModel) {
		val items = originalData
		// show all items in this category
		items.onEach {
			if (it.cloth.category == item.cloth.category) it.state = DressModel.State.UNSELECTED
		}
		postList(items)
	}

	fun onSave() = viewModelScope.launch(Dispatchers.IO) {
		data.value?.forEach {
			if (it.state != DressModel.State.SELECTED)
				return@forEach
			apiService.useCloth(it.cloth.id)
		}
		navigate(DressFragmentDirections.actionDressFragmentToClothListFragment())
	}

	private fun postList(items: List<DressModel>) {
		data.postValue(items.sort())
	}

	private fun List<DressModel>.sort() = this
		.sortedBy { it.cloth.name }
		.sortedBy { it.cloth.isUsed }
		.sortedBy { it.cloth.category.name }
//		.sortedBy { it.state.ordinal }
		.filter { it.state != DressModel.State.HIDDEN }
}
