package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.domain.common.ResourceProvider

val appModule = module {
    factory<ResourceProvider> {
        ResourceProvider.Base()
    }
}