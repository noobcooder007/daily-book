package com.bonsaisoftware.dailybook.domain

import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt

interface DebtRepository {
    fun getDebts(): List<Debt>
    fun addDebt(debt: Debt)
    fun editDebt(debt: Debt)
    fun getCreditCards(): List<CreditCard>
}