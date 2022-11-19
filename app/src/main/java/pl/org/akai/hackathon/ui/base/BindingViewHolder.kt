package pl.org.akai.hackathon.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingViewHolder<B : ViewBinding>(
	inflater: (LayoutInflater, ViewGroup?, Boolean) -> B,
	parent: ViewGroup,
	val b: B = inflater(
		LayoutInflater.from(parent.context),
		parent,
		false,
	),
) : RecyclerView.ViewHolder(b.root)
