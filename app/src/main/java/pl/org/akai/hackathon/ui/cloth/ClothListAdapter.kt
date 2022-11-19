package pl.org.akai.hackathon.ui.cloth

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import pl.org.akai.hackathon.data.model.Cloth
import pl.org.akai.hackathon.databinding.ClothListItemBinding
import pl.org.akai.hackathon.ext.onClick
import pl.org.akai.hackathon.ui.base.BindingViewHolder

class ClothListAdapter(
	diffCallback: DiffUtil.ItemCallback<Cloth>,
	private val vm: ClothListViewModel,
) : PagingDataAdapter<Cloth, BindingViewHolder<ClothListItemBinding>>(diffCallback) {

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int,
	) = BindingViewHolder(ClothListItemBinding::inflate, parent)

	override fun onBindViewHolder(
		holder: BindingViewHolder<ClothListItemBinding>,
		position: Int,
	) {
		val item = getItem(position)
		holder.b.item = item ?: return
		holder.b.root.onClick {
			vm.onItemClick(item)
		}
	}
}
