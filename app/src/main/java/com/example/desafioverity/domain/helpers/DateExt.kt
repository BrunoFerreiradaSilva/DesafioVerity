package com.example.desafioverity.domain.helpers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.showDataConvertingString(): String {
    val data = Date(this.time)
    val simpleDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return simpleDateFormat.format(data)
}