package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.domain.DebtRepository
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt

class DebtRepoImpl(private val debtManager: DebtManager) : DebtRepository {
    override fun getDebts(): List<Debt> {
        return debtManager.debts
    }

    override fun addDebt(debt: Debt) {
        debtManager.addDebt(debt)
    }

    override fun editDebt(debt: Debt) {
        debtManager.editDebt(debt)
    }

    override fun getCreditCards(): List<CreditCard> {
        return debtManager.getCreditCards()
    }
}