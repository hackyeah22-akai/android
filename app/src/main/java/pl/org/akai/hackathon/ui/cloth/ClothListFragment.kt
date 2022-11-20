package pl.org.akai.hackathon.ui.cloth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.hackathon.databinding.ListFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment

@AndroidEntryPoint
class ClothListFragment : BaseFragment<ListFragmentBinding>(ListFragmentBinding::inflate) {

	override val vm: ClothListViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm

		b.list.adapter = vm.adapter

//		b.list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
		b.list.layoutManager = GridLayoutManager(context, 2)

		vm.loadData()

		vm.clothClicked.observe(viewLifecycleOwner) {
			if (it != null) {
				MaterialAlertDialogBuilder(requireContext())
					.setTitle(it.name)
					.setMessage(it.category.name)
					.setNeutralButton("Cancel") { dialog, which ->
						// Respond to neutral button press
					}
					.setNegativeButton("Thrown") { dialog, which ->
						// Respond to negative button press
					}
					.setPositiveButton("Given/sold") { dialog, which ->
						// Respond to positive button press
					}
					.show()
				vm.endClothClicked()
			}
		}
	}
}
