package com.bonsaisoftware.dailybook.model

data class Debt(
    val debtId: Long,
    val debtName: String,
    val debtAmount: Long,
    val debtDate: String,
    val creditCard: CreditCard,
    val isPaid: Boolean = false,
    val expenseCategory: ExpenseCategory
)

enum class CreditCard(val cardName: String) {
    SANTANDER("Santander"),
    BBVA("BBVA"),
    MERCADOPAGO("Mercado Pago"),
    OTRO("Otro")
}
