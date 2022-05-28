package com.guilhermereisdev.pokedroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermereisdev.pokedroid.R
import com.guilhermereisdev.pokedroid.api.PokemonRepository
import com.guilhermereisdev.pokedroid.domain.Pokemon
import com.guilhermereisdev.pokedroid.domain.PokemonType
import com.guilhermereisdev.pokedroid.viewmodel.PokemonViewModel
import com.guilhermereisdev.pokedroid.viewmodel.PokemonViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

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

        viewModel.pokemons.observe(this) {
            loadRecyclerView(it)
        }
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}
