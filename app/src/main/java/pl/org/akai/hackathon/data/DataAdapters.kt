package pl.org.akai.hackathon.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DataAdapters {

	@ToJson
	fun toJson(value: LocalDate): String =
		value.format(DateTimeFormatter.ISO_DATE)

	@FromJson
	fun toLocalDate(value: String): LocalDate =
		LocalDate.parse(value, DateTimeFormatter.ISO_DATE)

	@ToJson
	fun toJson(value: ZonedDateTime): String =
		value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

	@FromJson
	fun toZonedDateTime(value: String): ZonedDateTime =
		ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME)
}
