package pl.org.akai.hackathon.data.model

open class ClothBase(
	var name: String,
	var photo: String,
)

class ClothCreate(
	name: String,
	photo: String,
	var categoryId: Int,
) : ClothBase(name, photo)

class Cloth(
	name: String,
	photo: String,
	val id: Int,
	val category: Category,
) : ClothBase(name, photo)
