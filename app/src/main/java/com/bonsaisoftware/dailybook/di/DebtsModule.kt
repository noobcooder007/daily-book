package com.bonsaisoftware.dailybook.di

import com.bonsaisoftware.dailybook.data.DebtRepoImpl
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.domain.DebtRepository
import com.bonsaisoftware.dailybook.presentation.DebtsViewModel
import org.koin.dsl.module

fun debtsModule(database: Database) = module {
    single<DebtRepository> { DebtRepoImpl(database) }
    factory { DebtsViewModel(get()) }
}