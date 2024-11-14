package com.bonsaisoftware.dailybook.di

import com.bonsaisoftware.dailybook.data.ExpenseRepoImpl
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.domain.ExpenseRepository
import com.bonsaisoftware.dailybook.presentation.ExpensesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun expensesModule(database: Database) = module {
    single<ExpenseRepository> { ExpenseRepoImpl(database) }
    factory { ExpensesViewModel(get()) }
}