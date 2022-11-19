package pl.org.akai.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.org.akai.hackathon.R

class BlankFragment : Fragment() {

	companion object {
		fun newInstance() = BlankFragment()
	}

	private lateinit var viewModel: BlankViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		return inflater.inflate(R.layout.fragment_blank, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
		// TODO: Use the ViewModel
	}

}