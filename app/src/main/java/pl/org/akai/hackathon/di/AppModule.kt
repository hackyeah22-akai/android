package pl.org.akai.hackathon.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.org.akai.hackathon.data.repositories.PreferencesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

	@Singleton
	@Provides
	fun provideContext(app: Application): Context = app

	@Singleton
	@Provides
	fun providePreferencesRepository(sharedPreferences: SharedPreferences) =
		PreferencesRepository(sharedPreferences)

	@Singleton
	@Provides
	fun provideSharedPref(
		@ApplicationContext
		context: Context,
	): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}
