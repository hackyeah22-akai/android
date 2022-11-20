package pl.org.akai.hackathon.ui.cloth

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Cloth

class ClothListPagingSource(
	private val apiService: ApiService,
	private val query: ClothListQuery,
	private val vm: ClothListViewModel,
) : PagingSource<Int, Cloth>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cloth> {
		return try {
			val page = params.key ?: 1
			val perPage = params.loadSize
			val data = apiService.getClothes(page, perPage)
			vm.unusedCount.postValue(data.count { !it.isUsed })
			val sortedData = when (query.sortBy) {
				ClothListQuery.SortBy.ADDED_AT -> data.sortedByDescending { it.createdAt }
				ClothListQuery.SortBy.USED_AT -> data.sortedByDescending { it.lastUsed }
				ClothListQuery.SortBy.NAME -> data.sortedBy { it.name }
					.sortedBy { it.category.name }
				ClothListQuery.SortBy.WASTE -> data.sortedByDescending { it.category.savings }
			}
			val filteredData = if (query.unused)
				sortedData.filter { !it.isUsed }
			else
				sortedData
			vm.count.postValue(filteredData.size)
			LoadResult.Page(
				data = filteredData,
				prevKey = null,//if (data.prevPage == null) null else page,
				nextKey = null,//if (data.nextPage == null) null else page + 1,
			)
		} catch (e: Exception) {
			e.printStackTrace()
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Cloth>): Int? {
		return null
	}
}
