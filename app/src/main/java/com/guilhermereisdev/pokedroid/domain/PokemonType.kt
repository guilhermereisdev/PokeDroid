package com.guilhermereisdev.pokedroid.domain

import com.guilhermereisdev.pokedroid.extensions.primeiraLetraMaiuscula

data class PokemonType(
    val name: String,
) {
    val formattedName = name.primeiraLetraMaiuscula()
}
