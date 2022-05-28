package com.guilhermereisdev.pokedroid.domain

import com.guilhermereisdev.pokedroid.extensions.primeiraLetraMaiuscula

data class Pokemon(
    val number: Int,
    val name: String,
    val types: List<PokemonType>,
) {
    val formattedName = name.primeiraLetraMaiuscula()
    val formattedNumber = number.toString().padStart(3, '0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
}
