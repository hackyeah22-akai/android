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
			title = "Problem",
			description = "Less than 1 % of used clothing is recycled into new garments. The Ellen MacArthur Foundation estimates that every year some USD 500 billion in value is lost due to clothing that is barely worn, not donated, recycled, or ends up in a landfill. ",
			imageDrawable = R.drawable.recycle,
			backgroundColorRes = R.color.md_theme_light_secondary,
			titleColorRes = R.color.md_theme_light_onSecondary,
			descriptionColorRes = R.color.md_theme_light_onSecondary
		))
		addSlide(AppIntroFragment.createInstance(
			title = "Solution",
			description = "We noticed that you added many clothes in this category. Maybe it's time to give them a second life? Help us reduce the global problem and profit from it! Just sell your clothes or maybe donate it or turn them to something else!",
			imageDrawable = R.drawable.recycle,
			backgroundColorRes = R.color.md_theme_dark_secondary,
			titleColorRes = R.color.md_theme_dark_onSecondary,
			descriptionColorRes = R.color.md_theme_dark_onSecondary
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