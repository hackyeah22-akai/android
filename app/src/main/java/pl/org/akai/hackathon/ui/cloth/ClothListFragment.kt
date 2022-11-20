package pl.org.akai.hackathon.ui.cloth

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
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
				val negativeDialog = MaterialAlertDialogBuilder(requireContext())
					.setTitle("What happened about recycling?")
					.setMessage("Did you know that you can recycle clothes? And not only that! With old clothes you can repair and repurpose, upcycle, resell and donate them. It's not that hard!")
					.setPositiveButton("I will do that!") { dialog, which ->
//						Snackbar.make(b.root, "Good job!", Snackbar.LENGTH_LONG).show()
					}
				MaterialAlertDialogBuilder(requireContext())
					.setTitle(it.name)
					.setMessage(it.category.name)
					.setNeutralButton("Cancel") { dialog, which ->
						// Respond to neutral button press
					}
					.setNegativeButton("Thrown") { dialog, which ->
						vm.throwCloth(it)
						negativeDialog.show()
					}
					.setPositiveButton("Given/sold") { dialog, which ->
						vm.sellCloth(it)
						Snackbar.make(b.root, "Good job!", Snackbar.LENGTH_LONG).show()
					}
					.show()
				vm.endClothClicked()
			}
		}

		b.sort.onItemClickListener = OnItemClickListener { _, _, pos, _ ->
			vm.query.sortBy = ClothListQuery.SortBy.values()[pos]
			vm.updateQuery()
		}

		b.unusedChip.setOnCheckedChangeListener { _, b ->
			vm.query.unused = b
			vm.updateQuery()
		}
	}
}
