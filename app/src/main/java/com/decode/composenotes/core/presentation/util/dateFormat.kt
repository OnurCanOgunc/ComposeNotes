package com.decode.composenotes.core.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateFormat(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
    val day = dateFormat.format(Date(timestamp))
    return day
}