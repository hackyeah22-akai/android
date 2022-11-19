package pl.org.akai.hackathon.ui.dress

import pl.org.akai.hackathon.data.model.Cloth

data class DressModel(
	val cloth: Cloth,
	var state: State = State.UNSELECTED,
) {

	enum class State {
		SELECTED,
		UNSELECTED,
		HIDDEN,
	}
}
