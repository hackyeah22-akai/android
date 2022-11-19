package pl.org.akai.hackathon.ui.add

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Category
import pl.org.akai.hackathon.data.model.ClothCreate
import pl.org.akai.hackathon.ui.base.DataViewModel
import java.io.ByteArrayOutputStream
import java.util.*
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

	private val _imageUrl = MutableLiveData("")
	val imageUrl: LiveData<String>
		get() = _imageUrl

	override suspend fun loadDataImpl(): List<Category> = apiService.getCategories()

	fun add(cloth: ClothCreate) {
		viewModelScope.launch(Dispatchers.IO) {
			apiService.addCloth(cloth)
			_navigateToList.postValue(true)
		}
	}

	fun uploadImage(imageBitmap: Bitmap) {
		viewModelScope.launch(Dispatchers.IO) {
			val stream = ByteArrayOutputStream()
			imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
			val byteArray = stream.toByteArray()
			val requestFile: RequestBody =
				byteArray.toRequestBody(
					"multipart/form-data".toMediaTypeOrNull(),
					0,
					byteArray.size
				)
			val photo =
				MultipartBody.Part.createFormData(
					"file_obj",
					"${UUID.randomUUID()}.jpg",
					requestFile
				)
			_imageUrl.postValue(apiService.uploadImage(photo).imageUrl)
		}
	}

	fun endNavigating() {
		_navigateToList.value = false
	}
}