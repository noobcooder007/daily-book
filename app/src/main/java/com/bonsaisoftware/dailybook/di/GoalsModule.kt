package com.bonsaisoftware.dailybook.di

import com.bonsaisoftware.dailybook.data.GoalRepoImpl
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.domain.GoalRepository
import com.bonsaisoftware.dailybook.presentation.GoalsViewModel
import org.koin.dsl.module

fun goalsModule(database: Database) = module {
    single<GoalRepository> { GoalRepoImpl(database) }
    factory { GoalsViewModel(get()) }
}