package ru.kraz.randomfriend.domain.common

import ru.kraz.randomfriend.R

interface ResourceProvider {
    fun getString(errorType: ErrorType): Int

    class Base : ResourceProvider {
        override fun getString(errorType: ErrorType): Int {
            return when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
            }
        }
    }
}