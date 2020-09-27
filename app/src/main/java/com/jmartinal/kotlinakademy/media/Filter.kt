package com.jmartinal.kotlinakademy.media

sealed class Filter {
    object None: Filter()
    class ByType(val type: Type): Filter()
}