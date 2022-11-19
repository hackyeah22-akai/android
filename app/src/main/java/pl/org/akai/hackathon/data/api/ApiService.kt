package pl.org.akai.hackathon.data.api

import pl.org.akai.hackathon.data.model.Cloth
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

	@GET("clothes")
	suspend fun getClothes(page: Int, perPage: Int): ApiList<Cloth>

	@GET("clothes/{id}")
	suspend fun getCloth(@Path("id") id: Int): Cloth

	@PUT("clothes/{id}")
	suspend fun updateCloth(@Path("id") id: Int): Cloth
}
