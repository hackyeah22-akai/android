package pl.org.akai.hackathon.data.api

import com.squareup.moshi.Json

data class ApiList<T>(
	val results: List<T>,

	@Json(name = "count")
	val totalCount: Int,
	@Json(name = "next")
	val nextPage: String?,
	@Json(name = "previous")
	val prevPage: String?,
)

