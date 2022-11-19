package pl.org.akai.hackathon.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Category
import pl.org.akai.hackathon.data.model.ClothCreate
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private var apiService: ApiService) : DataViewModel<List<Category>>(
	listOf(Category("Ładowanie kategorii...", "Ładowanie", 0))
) {
	val list: LiveData<Array<String>> = Transformations.map(data) { categories ->
		categories.map { it.name }.toTypedArray()
	}
	private val _navigateToList = MutableLiveData(false)
	val navigateToList: LiveData<Boolean>
		get() = _navigateToList

	override suspend fun loadDataImpl(): List<Category> = apiService.getCategories()

	fun add(cloth: ClothCreate) {
		viewModelScope.launch(Dispatchers.IO) {
			apiService.addCloth(cloth)
			_navigateToList.postValue(true)
		}
	}

	fun endNavigating() {
		_navigateToList.value = false
	}
}