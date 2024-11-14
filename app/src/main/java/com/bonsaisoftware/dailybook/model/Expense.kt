package com.bonsaisoftware.dailybook.model

import java.util.Date

data class Expense(
    val expenseId: Long,
    val expenseName: String,
    var expenseAmount: Long,
    val expenseDate: Date,
    val expenseCategory: ExpenseCategory,
    val expenseIsActive: Boolean = true,
    val expenseIsAnExpense: Boolean = false,
)

enum class ExpenseCategory(val categoryName: String) {
    FOOD("Food"),
    TRANSPORTATION("Transportation"),
    ENTERTAINMENT("Entertainment"),
    HEALTH("Health"),
    EDUCATION("Education"),
    SHOPPING("Shopping"),
    PERSONAL_CARE("Personal Care"),
    MISCELLANEOUS("Miscellaneous"),
    SAVINGS("Savings"),
    INVESTMENTS("Investments"),
    UTILITIES("Utilities"),
    INSURANCE("Insurance"),
    RENT("Rent"),
    TRAVEL("Travel"),
    OTHER("Other"),
}
