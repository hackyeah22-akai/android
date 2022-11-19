package pl.org.akai.hackathon.ui.cloth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.databinding.ListFragmentBinding
import pl.org.akai.hackathon.ui.base.BaseFragment
import pl.org.akai.hackathon.ui.base.ListLoadStateAdapter

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
