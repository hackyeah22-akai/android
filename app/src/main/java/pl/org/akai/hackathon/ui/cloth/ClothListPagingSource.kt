package pl.org.akai.hackathon.ui.cloth

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Cloth

class ClothListPagingSource(
	private val apiService: ApiService,
	private val query: ClothListQuery,
) : PagingSource<Int, Cloth>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cloth> {
		return try {
			val page = params.key ?: 1
			val perPage = params.loadSize
			val data = if (query.unused)
				apiService.getClothesUnused(page, perPage)
			else
				apiService.getClothes(page, perPage)
			val sortedData = when (query.sortBy) {
				ClothListQuery.SortBy.ADDED_AT -> data.sortedByDescending { it.createdAt }
				ClothListQuery.SortBy.USED_AT -> data.sortedByDescending { it.lastUsed }
				ClothListQuery.SortBy.NAME -> data.sortedBy { it.name }
					.sortedBy { it.category.name }
				ClothListQuery.SortBy.WASTE -> data.sortedByDescending { it.category.savings }
			}
//			val filteredData = if (query.unused)
//				sortedData.filter { it.lastUsed == null }
//			else
//				sortedData
			LoadResult.Page(
				data = sortedData,
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
