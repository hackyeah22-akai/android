package pl.org.akai.hackathon.ui.cloth

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Cloth
import pl.org.akai.hackathon.databinding.ClothListItemBinding
import pl.org.akai.hackathon.ui.base.DataListViewModel
import javax.inject.Inject

@HiltViewModel
class ClothListViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataListViewModel<Cloth, ClothListItemBinding, ClothListViewModel>(
	pagingSourceFactory = { ClothListPagingSource(apiService) },
	adapterFactory = { ClothListAdapter(ClothListComparator, it) },
) {

	fun onItemClick(item: Cloth) {
		//navigate(LostListFragmentDirections.actionLostListFragmentToItemFragment(item.id))
	}
}
