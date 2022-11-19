package pl.org.akai.hackathon.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Long.toISODate(): String {
	return LocalDate.ofEpochDay(this / (1000 * 60 * 60 * 24)).format(DateTimeFormatter.ISO_DATE)
}

fun Long.toLocalDate(): LocalDate {
	return LocalDate.ofEpochDay(this / (1000 * 60 * 60 * 24))
}