package com.bonsaisoftware.dailybook.util

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun currencyFormat(
    amount: BigDecimal,
    locale: Locale = Locale.getDefault()
): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(amount.divide(BigDecimal(100)))
}