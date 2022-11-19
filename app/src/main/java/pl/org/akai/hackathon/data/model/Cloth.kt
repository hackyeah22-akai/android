package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json

open class ClothBase(
	var name: String,
	var photo: String,
)

class ClothCreate(
	name: String,
	photo: String,
	@Json(name = "category_id")
	var categoryId: Int,
) : ClothBase(name, photo)

class Cloth(
	name: String,
	photo: String,
	val id: Int,
	val category: Category,
) : ClothBase(name, photo)
