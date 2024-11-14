package com.bonsaisoftware.dailybook.util

fun currencyFormat(s: String): String {
    if (s.isEmpty()) return ""
    else if (s.length <= 2) return "$s.00"
    return StringBuffer(s).insert(s.length-2, ".").toString()
}