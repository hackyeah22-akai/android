package pl.org.akai.hackathon.ui.dress

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.org.akai.hackathon.databinding.ClothListItemBinding
import pl.org.akai.hackathon.ext.onClick
import pl.org.akai.hackathon.ui.base.BindingViewHolder

class DressAdapter(
	private val vm: DressViewModel,
) : RecyclerView.Adapter<BindingViewHolder<ClothListItemBinding>>() {

	var items: List<DressModel> = listOf()

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int,
	) = BindingViewHolder(ClothListItemBinding::inflate, parent)

	override fun onBindViewHolder(
		holder: BindingViewHolder<ClothListItemBinding>,
		position: Int,
	) {
		val item = items[position]
		holder.b.item = item
		holder.b.root.onClick {
			when (item.state) {
				DressModel.State.SELECTED -> vm.onClothUnselected(item)
				DressModel.State.UNSELECTED -> vm.onClothSelected(item)
				else -> {}
			}
		}
	}

	override fun getItemCount() = items.count { it.state != DressModel.State.HIDDEN }
}
