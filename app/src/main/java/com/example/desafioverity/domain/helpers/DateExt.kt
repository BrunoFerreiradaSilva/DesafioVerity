package com.example.desafioverity.domain.helpers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val FORMAT_DATE_ENGLISH = "MMMM dd, yyyy"

fun Date.showDataConvertingString(): String {
    val data = Date(this.time)
    val simpleDateFormat = SimpleDateFormat(FORMAT_DATE_ENGLISH, Locale.getDefault())
    return simpleDateFormat.format(data)
}