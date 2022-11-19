package pl.org.akai.hackathon.ui.cloth

import androidx.recyclerview.widget.DiffUtil
import pl.org.akai.hackathon.data.model.Cloth

object ClothListComparator : DiffUtil.ItemCallback<Cloth>() {

	override fun areItemsTheSame(oldItem: Cloth, newItem: Cloth): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: Cloth, newItem: Cloth): Boolean {
		return oldItem == newItem
	}
}
