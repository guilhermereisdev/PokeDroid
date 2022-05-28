package com.guilhermereisdev.pokedroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermereisdev.pokedroid.R
import com.guilhermereisdev.pokedroid.api.PokemonRepository
import com.guilhermereisdev.pokedroid.domain.Pokemon
import com.guilhermereisdev.pokedroid.domain.PokemonType
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemons)

//        val charmander = Pokemon(
//            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
//            4,
//            "Charmander",
//            listOf(PokemonType("Fire"))
//        )
//        val pokemons = listOf(charmander, charmander, charmander, charmander)

        Thread(Runnable {
            loadPokemons()
        }).start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()
        pokemonsApiResult?.results?.let {
            Log.d("POKEMON_API", it.toString())

            val pokemons: List<Pokemon?> = it.map { pokemonResult ->
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
            }

            val layoutManager = GridLayoutManager(this, 3)

            recyclerView.post {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }
    }
}