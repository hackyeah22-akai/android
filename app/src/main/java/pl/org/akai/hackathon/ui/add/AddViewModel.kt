package pl.org.akai.hackathon.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : BaseViewModel() {
	private val _list = MutableLiveData<Array<String>>(arrayOf("Elo", "hej"))
	val list: LiveData<Array<String>>
		get() = _list
}