package com.memije.examenpokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memije.examenpokemon.databinding.PokemonItemBinding
import com.memije.examenpokemon.domain.model.Pokemon

class PokemonAdapter(private val itemClickListener: OnPokemonClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    interface OnPokemonClickListener {
        fun onItemClick(name: String)
    }

    private var pokemons = mutableListOf<Pokemon>()

    fun setPokemonList(pokemons: List<Pokemon>) {
        this.pokemons = pokemons.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = PokemonItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.tvName.text = pokemon.name

        holder.binding.cvPokemon.setOnClickListener {
            itemClickListener.onItemClick(pokemon.name)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}

class MainViewHolder(val binding: PokemonItemBinding) : RecyclerView.ViewHolder(binding.root) {}