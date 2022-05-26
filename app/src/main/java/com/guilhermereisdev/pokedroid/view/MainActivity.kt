package com.guilhermereisdev.pokedroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermereisdev.pokedroid.R
import com.guilhermereisdev.pokedroid.domain.Pokemon
import com.guilhermereisdev.pokedroid.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val charmander = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
            4,
            "Charmander",
            listOf(
                PokemonType(
                    "Fire"
                )
            )
        )
        val pokemons = listOf(
            charmander,
            charmander,
            charmander,
            charmander,
            charmander,
            charmander,
            charmander,
            charmander,
            charmander,
            charmander,
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
        val layoutManager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}