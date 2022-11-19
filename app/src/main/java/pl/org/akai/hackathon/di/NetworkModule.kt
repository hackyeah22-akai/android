package pl.org.akai.hackathon.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.org.akai.hackathon.data.DataAdapters
import pl.org.akai.hackathon.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	@Singleton
	@Provides
	fun provideMoshi(): Moshi =
		Moshi.Builder()
			.add(DataAdapters())
			.addLast(KotlinJsonAdapterFactory())
			.build()

	@Singleton
	@Provides
	fun provideOkHttpClient(context: Context): OkHttpClient =
		OkHttpClient.Builder()
			.addInterceptor {
				val prefs = context.getSharedPreferences("user", Context.MODE_PRIVATE)
				val token = prefs.getString("token", null)
				if (token != null) {
					return@addInterceptor it.proceed(it.request()
						.newBuilder()
						.addHeader("Authorization", "Bearer $token")
						.build())
				}
				it.proceed(it.request())
			}
			.build()

	@Singleton
	@Provides
	fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
		Retrofit.Builder()
			.baseUrl("http://dupa.com:8080/api/v1/")
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.build()

	@Singleton
	@Provides
	fun provideFindItApi(retrofit: Retrofit): ApiService =
		retrofit.create(ApiService::class.java)

}
