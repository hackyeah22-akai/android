package pl.org.akai.hackathon.ui.tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.hackathon.R

@AndroidEntryPoint
class TooMuchActivity : AppIntro() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTransformer(AppIntroPageTransformerType.Parallax(
			titleParallaxFactor = 1.0,
			imageParallaxFactor = -1.0,
			descriptionParallaxFactor = 2.0,
		))
		addSlide(AppIntroFragment.createInstance(
			title = "Gościu",
			description = "Za dużo masz już ubrań",
			imageDrawable = R.drawable.profile,
			backgroundColorRes = R.color.md_theme_light_secondary,
		))
		addSlide(AppIntroFragment.createInstance(
			title = "Lepiej się porządnie zastanów",
			description = "Czy na pewno chcesz marnwać świat",
			imageDrawable = R.drawable.profile,
			backgroundColorRes = R.color.md_theme_dark_secondary,
		))
	}

	override fun onSkipPressed(currentFragment: Fragment?) {
		super.onSkipPressed(currentFragment)
		finish()
	}

	override fun onDonePressed(currentFragment: Fragment?) {
		super.onDonePressed(currentFragment)
		finish()
	}
}