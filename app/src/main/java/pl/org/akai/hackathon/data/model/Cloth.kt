package pl.org.akai.hackathon.data.model

open class ClothBase(
	val name: String,
	val photo: String,
)

class ClothCreate(
	name: String,
	photo: String,
	val categoryId: Int,
) : ClothBase(name, photo)

class Cloth(
	name: String,
	photo: String,
	val id: Int,
	val category: Category,
) : ClothBase(name, photo)
