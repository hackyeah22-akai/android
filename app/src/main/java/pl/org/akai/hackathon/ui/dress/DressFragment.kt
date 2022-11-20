package pl.org.akai.hackathon.ui.dress

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.DressFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment

@AndroidEntryPoint
class DressFragment : BaseFragment<DressFragmentBinding>(DressFragmentBinding::inflate) {

	override val vm: DressViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		b.vm = vm

		val adapter = DressAdapter(vm)
		b.list.adapter = adapter
		b.list.layoutManager = LinearLayoutManager(
			requireContext(),
			LinearLayoutManager.VERTICAL,
			false,
		)

		vm.data.observe(viewLifecycleOwner) { newList ->
			val oldList = adapter.items
			val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
				override fun getOldListSize() = oldList.size
				override fun getNewListSize() = newList.size
				override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = false

				override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
					return oldList[oldItemPosition].cloth.id == newList[newItemPosition].cloth.id
				}
			})
			adapter.items = newList
			diff.dispatchUpdatesTo(adapter)
		}

		lifecycleScope.launch(Dispatchers.IO) {
			vm.loadData()
		}
	}
}
