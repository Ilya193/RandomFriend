package ru.kraz.randomfriend

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.kraz.randomfriend.di.appModule
import ru.kraz.randomfriend.di.firebaseModule
import ru.kraz.randomfriend.di.mappersModule
import ru.kraz.randomfriend.di.networkModule
import ru.kraz.randomfriend.di.repositoryModule
import ru.kraz.randomfriend.di.useCasesModule
import ru.kraz.randomfriend.di.viewModelsModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                viewModelsModule,
                networkModule,
                firebaseModule,
                mappersModule,
                repositoryModule,
                useCasesModule,
            )
        }
    }
}