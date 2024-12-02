package com.bonsaisoftware.dailybook.di

import com.bonsaisoftware.dailybook.data.BagRepoImpl
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.domain.BagRepository
import com.bonsaisoftware.dailybook.presentation.BagsViewModel
import org.koin.dsl.module

fun bagsModule(database: Database) = module {
    single<BagRepository> { BagRepoImpl(database) }
    factory { BagsViewModel(get()) }
}