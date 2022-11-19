package pl.org.akai.hackathon.data.repositories

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(
	private val sharedPref: SharedPreferences,
) {
	var firstLaunch: Boolean
		get() = sharedPref.getBoolean("firstLaunch", true)
		set(value) = sharedPref.edit().putBoolean("firstLaunch", value).apply()
}