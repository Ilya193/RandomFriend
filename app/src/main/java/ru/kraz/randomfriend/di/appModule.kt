package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.domain.ResourceProvider

val appModule = module {
    factory<ResourceProvider> {
        ResourceProvider.Base()
    }
}