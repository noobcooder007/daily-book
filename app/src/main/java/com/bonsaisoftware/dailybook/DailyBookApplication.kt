package com.bonsaisoftware.dailybook

import android.app.Application
import app.cash.sqldelight.ColumnAdapter
import com.bonsaisoftware.dailybook.data.DatabaseDriverFactory
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.db.ExpenseEntity
import com.bonsaisoftware.dailybook.di.expensesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.text.SimpleDateFormat
import java.util.Date

class DailyBookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DailyBookApplication)
            modules(
                expensesModule(
                    Database.invoke(
                        DatabaseDriverFactory(
                            this@DailyBookApplication
                        ).createDriver()
                    )
                )
            )
        }
    }
}