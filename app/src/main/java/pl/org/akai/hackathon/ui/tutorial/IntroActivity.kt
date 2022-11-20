package pl.org.akai.hackathon.ui.tutorial

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
			description = "How much did you pay for the clothes in your closet? If you have the receipts, you can calculate this. However, there is a cost behind each dress, pair of jeans, shirt, and sock that goes unnoticed by most people: the cost to the environment.",
			imageDrawable = R.drawable.wardrobe,
			backgroundColorRes = R.color.md_theme_dark_primary,
			titleColorRes = R.color.md_theme_dark_onPrimary,
			descriptionColorRes = R.color.md_theme_dark_onPrimary
		))
		addSlide(AppIntroFragment.createInstance(
			title = "How are jeans made?",
			description = "According to figures from the United Nations Environment Programme (UNEP), it takes 3,781 liters of water to make a pair of jeans, from the production of the cotton to the delivery of the final product to the store. That equates to the emission of around 33.4 kilograms of carbon equivalent.",
			imageDrawable = R.drawable.jeans,
			backgroundColorRes = R.color.md_theme_light_primary,
			titleColorRes = R.color.md_theme_light_onPrimary,
			descriptionColorRes = R.color.md_theme_light_onPrimary
		))
		addSlide(AppIntroFragment.createInstance(
			title = "We are here to reduce that waste!",
			description = "Add all your clothes using our app and help the environment! See your corbon footprint statistics. Monitor your clothes usage and be aware of what you use and don't.",
			imageDrawable = R.drawable.like,
			backgroundColorRes = R.color.md_theme_dark_primary,
			titleColorRes = R.color.md_theme_dark_onPrimary,
			descriptionColorRes = R.color.md_theme_dark_onPrimary
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