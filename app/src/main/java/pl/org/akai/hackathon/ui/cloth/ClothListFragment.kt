package pl.org.akai.hackathon.ui.cloth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
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
	}
}
