package com.guilhermereisdev.pokedroid.extensions

import java.util.*

fun String.primeiraLetraMaiuscula(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}