package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.db.DebtQueries
import com.bonsaisoftware.dailybook.domain.DebtRepository
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import java.util.Date

class DebtRepoImpl(database: Database) : DebtRepository {
    private val debtQueries: DebtQueries = database.debtQueries
    override fun getDebts(): List<Debt> {
        return debtQueries.selectAll().executeAsList().map {
            Debt(
                debtId = it.debtId,
                debtName = it.debtName,
                debtAmount = it.debtAmount,
                debtDate = Date(it.debtDate),
                debtCreditCard = CreditCard.valueOf(it.debtCreditCard),
                debtIsActive = it.debtIsActive == 1L
            )
        }
    }

    override fun addDebt(debt: Debt) {
        debtQueries.transaction {
            debtQueries.insert(
                debt.debtName.trim(),
                debt.debtAmount,
                debt.debtDate.toString(),
                debt.debtCreditCard.name
            )
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
                debtCreditCard = CreditCard.valueOf(it.debtCreditCard)
            )
        }
    }
}