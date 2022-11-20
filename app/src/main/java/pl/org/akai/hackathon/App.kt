package pl.org.akai.hackathon

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@SuppressLint("HardwareIds")
@HiltAndroidApp
class App : Application(), CoroutineScope {

	override val coroutineContext = Dispatchers.IO + Job()

	val deviceId by lazy {
		Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
	}

	override fun onCreate() {
		super.onCreate()
		DynamicColors.applyToActivitiesIfAvailable(this)
	}
}

