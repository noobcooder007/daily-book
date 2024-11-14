package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.domain.ExpenseRepository
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class ExpenseRepoImpl(private val database: Database) :
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
                expense.expenseName,
                expense.expenseAmount,
                expense.expenseDate.toString(),
                expense.expenseCategory.name,
                if (expense.expenseIsAnExpense) 1L else 0L
            )
        }
    }

    override fun editExpense(expense: Expense) {
        expenseQueries.transaction {
            expenseQueries.update(
                expense.expenseAmount,
                expense.expenseCategory.name,
                if (expense.expenseIsAnExpense) 1L else 0L,
                expense.expenseId
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
        // TODO: Implement this
        return emptyList()
    }
}