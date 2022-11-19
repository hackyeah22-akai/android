package pl.org.akai.hackathon.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.hackathon.R
import pl.org.akai.hackathon.data.repositories.PreferencesRepository
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : AppIntro() {
	@Inject
	lateinit var preferencesRepository: PreferencesRepository
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTransformer(AppIntroPageTransformerType.Parallax(
			titleParallaxFactor = 1.0,
			imageParallaxFactor = -1.0,
			descriptionParallaxFactor = 2.0,
		))
		addSlide(AppIntroFragment.createInstance(
			title = "Welcome...",
			description = "This is the first slide of the example",
			imageDrawable = R.drawable.profile,
			backgroundColorRes = R.color.md_theme_dark_primary,
		))
		addSlide(AppIntroFragment.createInstance(
			title = "...Let's get started!",
			description = "This is the last slide, I won't annoy you more :)",
			imageDrawable = R.drawable.profile,
			backgroundColorRes = R.color.md_theme_light_primary,
		))
	}

	override fun onSkipPressed(currentFragment: Fragment?) {
		super.onSkipPressed(currentFragment)
		preferencesRepository.firstLaunch = false
		finish()
	}

	override fun onDonePressed(currentFragment: Fragment?) {
		super.onDonePressed(currentFragment)
		preferencesRepository.firstLaunch = false
		finish()
	}
}