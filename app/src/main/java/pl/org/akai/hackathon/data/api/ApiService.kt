package pl.org.akai.hackathon.data.api

import okhttp3.MultipartBody
import pl.org.akai.hackathon.data.model.*
import retrofit2.http.*

interface ApiService {

	@GET("clothes")
	suspend fun getClothes(
		@Query("page")
		page: Int,
		@Query("per_page")
		perPage: Int,
	): List<Cloth>

	@GET("clothes/unused")
	suspend fun getClothesUnused(
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
	): TooMuch

	@PUT("clothes/{id}")
	suspend fun updateCloth(
		@Path("id")
		id: Int,
	): Cloth

	@POST("clothes/use/{id}")
	suspend fun useCloth(
		@Path("id")
		id: Int,
	)

	@DELETE("clothes/sell/{id}")
	suspend fun sellCloth(
		@Path("id")
		id: Int,
	)

	@DELETE("clothes/throw/{id}")
	suspend fun throwCloth(
		@Path("id")
		id: Int,
	)

	@GET("clothes/unavailable")
	suspend fun getUnavailableClothes(): List<Cloth>

	@Multipart
	@POST("clothes/images")
	suspend fun uploadImage(
		@Part
		file_obj: MultipartBody.Part,
	): ImageUrl

	@GET("category")
	suspend fun getCategories(): List<Category>
}
