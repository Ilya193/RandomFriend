package ru.kraz.randomfriend.di

import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kraz.randomfriend.data.people.PeopleService

val networkModule = module {
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