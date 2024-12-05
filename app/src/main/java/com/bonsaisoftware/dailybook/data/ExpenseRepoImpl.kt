package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.domain.ExpenseRepository
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import java.util.Date

class ExpenseRepoImpl(database: Database) :
    ExpenseRepository {
    private val expenseQueries = database.expenseQueries
    override fun getAllExpenses(): List<Expense> {
        return expenseQueries.selectAll().executeAsList().map {
            Expense(
                expenseId = it.expenseId,
                expenseName = it.expenseName,
                expenseAmount = it.expenseAmount,
                expenseDate = Date(it.expenseDate),
                expenseCategory = ExpenseCategory.valueOf(it.expenseCategory),
                expenseIsActive = it.expenseIsActive == 1L,
                expenseIsAnExpense = it.expenseIsAnExpense == 1L
            )
        }
    }

    override fun addExpense(expense: Expense) {
        expenseQueries.transaction {
            expenseQueries.insert(
                expenseName = expense.expenseName.trim(),
                expenseAmount = expense.expenseAmount,
                expenseDate = expense.expenseDate.toString(),
                expenseCategory = expense.expenseCategory.name,
                expenseIsAnExpense = if (expense.expenseIsAnExpense) 1L else 0L,
                expenseDebtId = expense.expenseDebtId
            )
        }
    }

    override fun editExpense(expense: Expense) {
        expenseQueries.transaction {
            expenseQueries.update(
                expenseName = expense.expenseName.trim(),
                expenseAmount = expense.expenseAmount,
                expenseCategory = expense.expenseCategory.name,
                expenseIsAnExpense = if (expense.expenseIsAnExpense) 1L else 0L,
                expenseId = expense.expenseId
            )
        }
    }

    override fun getExpenseWithID(id: Long): Expense? {
        val expense = expenseQueries.selectById(id).executeAsOneOrNull()
        return expense?.let {
            Expense(
                expenseId = it.expenseId,
                expenseName = it.expenseName,
                expenseAmount = it.expenseAmount,
                expenseCategory = ExpenseCategory.valueOf(it.expenseCategory),
                expenseDate = Date(it.expenseDate),
                expenseIsActive = it.expenseIsActive == 1L,
                expenseIsAnExpense = it.expenseIsAnExpense == 1L
            )
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
        return ExpenseCategory.entries
    }
}