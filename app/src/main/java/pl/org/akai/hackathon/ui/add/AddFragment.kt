package pl.org.akai.hackathon.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.mikepenz.iconics.utils.colorRes
import com.mikepenz.iconics.utils.sizeDp
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.hackathon.data.model.ClothCreate
import pl.org.akai.hackathon.databinding.AddFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment

@AndroidEntryPoint
class AddFragment : BaseFragment<AddFragmentBinding>(AddFragmentBinding::inflate) {
	override val vm: AddViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		b.vm = vm
		b.model = ClothCreate("", "", 0)
		b.button.icon = IconicsDrawable(requireContext(), CommunityMaterial.Icon3.cmd_plus).apply {
			sizeDp = 24
			colorRes =
				com.github.bassaer.chatmessageview.R.color.blueGray300 //todo: change this pls
		}
	}
}