package pl.org.akai.hackathon.data.api

import pl.org.akai.hackathon.data.model.Category
import pl.org.akai.hackathon.data.model.Cloth
import pl.org.akai.hackathon.data.model.ClothCreate
import retrofit2.http.*

interface ApiService {

	@GET("clothes")
	suspend fun getClothes(
		@Query("page")
		page: Int,
		@Query("per_page")
		perPage: Int,
	): List<Cloth>

	@GET("clothes/{id}")
	suspend fun getCloth(
		@Path("id")
		id: Int,
	): Cloth

	@POST("clothes")
	suspend fun addCloth(
		@Body
		cloth: ClothCreate,
	)

	@PUT("clothes/{id}")
	suspend fun updateCloth(
		@Path("id")
		id: Int,
	): Cloth

	@GET("category")
	suspend fun getCategories(): List<Category>
}
