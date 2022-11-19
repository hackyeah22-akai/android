package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json

data class ImageUrl(
	@Json(name = "url")
	val imageUrl: String,
)