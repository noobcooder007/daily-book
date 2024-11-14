package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.model.ExpenseCategory

object DebtManager {
    private val _debts = mutableListOf(
        Debt(
            debtId = 1,
            debtName = "Groceries",
            debtAmount = -50,
            debtDate = "2023-04-01",
            creditCard = CreditCard.MERCADOPAGO,
            expenseCategory = ExpenseCategory.FOOD,
        )
    )

    val debts = _debts

    fun addDebt(debt: Debt) {
        _debts.add(debt)
    }

    fun editDebt(debt: Debt) {
        val index = debts.indexOfFirst {
            it.debtId == debt.debtId
        }
        if (index != -1) {
            _debts[index] = debts[index].copy(
                debtName = debt.debtName,
                debtAmount = debt.debtAmount,
                creditCard = debt.creditCard,
                expenseCategory = debt.expenseCategory
            )
        }
    }

    fun getCreditCards(): List<CreditCard> {
        return CreditCard.entries
    }
}