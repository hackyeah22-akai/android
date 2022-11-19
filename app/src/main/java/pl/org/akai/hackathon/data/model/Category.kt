package pl.org.akai.hackathon.data.model

open class CategoryBase(
	val name: String,
	val savings: String,
)

class CategoryCreate(
	name: String,
	savings: String,
) : CategoryBase(name, savings)

class Category(
	name: String,
	savings: String,
	val id: Int,
) : CategoryBase(name, savings)
