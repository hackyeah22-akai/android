package pl.org.akai.hackathon.ui.cloth

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Cloth

class ClothListPagingSource(
	private val apiService: ApiService,
) : PagingSource<Int, Cloth>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cloth> {
		return try {
			val page = params.key ?: 1
			val perPage = params.loadSize
			val data = apiService.getClothes(page, perPage)
			LoadResult.Page(
				data = data,
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
