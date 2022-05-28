package com.guilhermereisdev.pokedroid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guilhermereisdev.pokedroid.R
import com.guilhermereisdev.pokedroid.domain.Pokemon
import com.guilhermereisdev.pokedroid.extensions.primeiraLetraMaiuscula

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemonAvatar)
            val tvPokemonNumber = findViewById<TextView>(R.id.tvPokemonNumber)
            val tvPokemonName = findViewById<TextView>(R.id.tvPokemonName)
            val tvType1 = findViewById<TextView>(R.id.tvType1)
            val tvType2 = findViewById<TextView>(R.id.tvType2)

            item?.let { it ->
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)
                tvPokemonNumber.text = item.formattedNumber
                tvPokemonName.text = item.formattedName
                tvType1.text = item.types[0].name.primeiraLetraMaiuscula()
                if (item.types.size > 1) {
                    tvType2.apply {
                        visibility = View.VISIBLE
                        text = item.types[1].name.primeiraLetraMaiuscula()
                    }
                    tvType1.apply {
                        layoutParams.width = WRAP_CONTENT
                    }
                } else {
                    tvType2.apply {
                        visibility = View.GONE
                    }
                    tvType1.apply {
                        layoutParams.width = MATCH_PARENT
                    }
                }
            }
        }
    }
}
