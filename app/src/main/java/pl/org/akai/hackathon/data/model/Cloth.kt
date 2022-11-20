package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.LocalDate

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
	@Json(name = "last_used")
	val lastUsed: LocalDate?,
) : ClothBase(name, photo) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Cloth) return false

		if (id != other.id) return false
		if (category != other.category) return false

		return true
	}

	override fun hashCode(): Int {
		var result = id
		result = 31 * result + category.hashCode()
		return result
	}
}

data class ImageUrl(
	@Json(name = "url")
	val imageUrl: String,
)

data class TooMuch(
	@Json(name = "too_much") //todo
	val tooMuch: Boolean,
)
