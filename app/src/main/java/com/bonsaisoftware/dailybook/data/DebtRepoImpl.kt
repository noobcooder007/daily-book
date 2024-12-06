package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.db.DebtQueries
import com.bonsaisoftware.dailybook.db.ExpenseQueries
import com.bonsaisoftware.dailybook.domain.DebtRepository
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import java.util.Date

class DebtRepoImpl(database: Database) : DebtRepository {
    private val debtQueries: DebtQueries = database.debtQueries
    private val expenseQueries: ExpenseQueries = database.expenseQueries
    override fun getDebts(): List<Debt> {
        return debtQueries.selectAll().executeAsList().map {
            Debt(
                debtId = it.debtId,
                debtName = it.debtName,
                debtAmount = it.debtAmount,
                debtDate = Date(it.debtDate),
                debtCreditCard = CreditCard.valueOf(it.debtCreditCard),
                debtIsActive = it.debtIsActive == 1L,
                debtIsAPayment = it.debtIsAPayment == 1L
            )
        }
    }

    override fun addDebt(debt: Debt) {
        debtQueries.transaction {
            debtQueries.insert(
                debtName = debt.debtName.trim(),
                debtAmount = debt.debtAmount,
                debtDate = debt.debtDate.toString(),
                debtCreditCard = debt.debtCreditCard.name,
                debtIsAPayment = if (debt.debtIsAPayment) 1L else 0L
            )
            if (debt.debtIsAPayment) {
                expenseQueries.insert(
                    expenseName = "Pago a tarjeta de crédito",
                    expenseAmount = debt.debtAmount,
                    expenseDate = debt.debtDate.toString(),
                    expenseCategory = ExpenseCategory.OTHER.name,
                    expenseIsAnExpense = 1L,
                    expenseCanEdit = 0L
                )
            }
        }
    }

    override fun editDebt(debt: Debt) {
        debtQueries.transaction {
            debtQueries.update(
                debt.debtName.trim(),
                debt.debtAmount,
                debt.debtCreditCard.name,
                debt.debtId
            )
        }
    }

    override fun getCreditCards(): List<CreditCard> {
        return CreditCard.entries
    }

    override fun getDebtWithID(id: Long): Debt? {
        val debt = debtQueries.selectBy(id).executeAsOneOrNull()
        return debt?.let {
            Debt(
                debtId = it.debtId,
                debtName = it.debtName,
                debtAmount = it.debtAmount,
                debtDate = Date(it.debtDate),
                debtIsActive = it.debtIsActive == 1L,
                debtCreditCard = CreditCard.valueOf(it.debtCreditCard),
                debtIsAPayment = it.debtIsAPayment == 1L
            )
        }
    }
}