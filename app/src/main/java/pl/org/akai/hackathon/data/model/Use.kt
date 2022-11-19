package pl.org.akai.hackathon.data.model

import java.time.LocalDate

open class UseBase(
	val date: LocalDate,
)

class UseCreate(
	date: LocalDate,
	val clothId: Int,
) : UseBase(date)

class Use(
	date: LocalDate,
	val cloth: Cloth,
) : UseBase(date)
