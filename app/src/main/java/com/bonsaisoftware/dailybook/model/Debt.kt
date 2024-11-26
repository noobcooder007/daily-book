package com.bonsaisoftware.dailybook.model

import java.util.Date

data class Debt(
    val debtId: Long,
    val debtName: String,
    val debtAmount: Long,
    val debtDate: Date,
    val debtCreditCard: CreditCard,
    val debtIsActive: Boolean = true
)

enum class CreditCard(val cardName: String) {
    SANTANDER("Santander"),
    BBVA("BBVA"),
    MERCADOPAGO("Mercado Pago"),
    COPPEL("Coppel"),
    OTHER("Otro")
}
