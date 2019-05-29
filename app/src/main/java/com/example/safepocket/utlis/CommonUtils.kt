package com.example.safepocket.utlis

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun dateFormat(date: LocalDate, pattern: String = "EE, MMMM dd"): String {

    val formatter = DateTimeFormatter.ofPattern(pattern)
    return date.format(formatter)
}

/**
 * @param amount the amount to format, can be in every type eg double, int...
 * format given amount in locale currency
 */
fun <T> currencyFormat(amount: T) = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(amount)