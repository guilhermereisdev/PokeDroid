package com.guilhermereisdev.pokedroid.api;

import android.util.Log
import com.guilhermereisdev.pokedroid.api.model.PokemonApiResult
import com.guilhermereisdev.pokedroid.api.model.PokemonsApiResult
import com.guilhermereisdev.pokedroid.domain.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 386): PokemonsApiResult? {
        val call = service.listPokemons(limit)
        return call.execute().body()
//        call.enqueue(object : Callback<PokemonsApiResult> {
//            override fun onResponse(
//                call: Call<PokemonsApiResult>,
//                response: Response<PokemonsApiResult>
//            ) {
//                Log.d("POKEMON_API", "Pokémons list loaded")
//                if (response.isSuccessful) {
//                    val body = response.body()
//                    body?.results?.let {
//                        Log.d("POKEMON_API", it[0].name)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
//                Log.e("POKEMON_API", "Error loading Pokémons list. -> $t", t)
//            }
//        })
    }

    fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)
        return call.execute().body()
    }
}
