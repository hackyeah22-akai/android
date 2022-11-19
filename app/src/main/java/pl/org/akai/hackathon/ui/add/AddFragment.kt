package pl.org.akai.hackathon.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.mikepenz.iconics.utils.colorRes
import com.mikepenz.iconics.utils.sizeDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}
		b.button.setOnClickListener {
			val category = vm.data.value?.firstOrNull { it.name == b.category.text.toString() }
			val model = b.model
			model?.categoryId = category?.id ?: 1 //todo
			if (model == null) return@setOnClickListener
			vm.add(model)
		}
		vm.navigateToList.observe(viewLifecycleOwner) {
			if (it) {
				findNavController().navigate(AddFragmentDirections.actionAddFragmentToClothListFragment())
				vm.endNavigating()
			}
		}
	}
}