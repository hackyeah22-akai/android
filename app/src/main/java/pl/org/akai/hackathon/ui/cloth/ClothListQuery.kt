package pl.org.akai.hackathon.ui.cloth

data class ClothListQuery(
	var sortBy: SortBy = SortBy.USED_AT,
	var unused: Boolean = false,
) {

	enum class SortBy {
		ADDED_AT,
		USED_AT,
		NAME,
		WASTE,
	}
}
