package ru.kraz.randomfriend

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }
}

val module = module {
    viewModel<RandomPeopleViewModel> {
        RandomPeopleViewModel(get())
    }

    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single<PeopleService> {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(get())
            .build()
            .create(PeopleService::class.java)
    }
}