package com.guilhermereisdev.pokedroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilhermereisdev.pokedroid.api.PokemonRepository
import com.guilhermereisdev.pokedroid.domain.Pokemon

class PokemonViewModel : ViewModel() {

    val pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread { loadPokemons() }.start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            Log.d("POKEMON_API", it.toString())

            pokemons.postValue(it.map { pokemonResult ->
                val number =
                    pokemonResult.url
                        .replace("https://pokeapi.co/api/v2/pokemon/", "")
                        .replace("/", "")
                        .toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type -> type.type }
                    )
                }
            })
        }
    }
}
