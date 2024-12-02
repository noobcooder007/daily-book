package com.bonsaisoftware.dailybook

import android.app.Application
import com.bonsaisoftware.dailybook.data.DatabaseDriverFactory
import com.bonsaisoftware.dailybook.db.Database
import com.bonsaisoftware.dailybook.di.bagsModule
import com.bonsaisoftware.dailybook.di.debtsModule
import com.bonsaisoftware.dailybook.di.expensesModule
import com.bonsaisoftware.dailybook.di.goalsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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
                ),
                debtsModule(
                    Database.invoke(
                        DatabaseDriverFactory(
                            this@DailyBookApplication
                        ).createDriver()
                    )
                ),
                bagsModule(
                    Database.invoke(
                        DatabaseDriverFactory(
                            this@DailyBookApplication
                        ).createDriver()
                    )
                ),
                goalsModule(
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