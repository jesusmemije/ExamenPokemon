package com.memije.examenpokemon.domain

import com.memije.examenpokemon.data.PokemonRepository
import com.memije.examenpokemon.data.database.entities.toDatabase
import com.memije.examenpokemon.domain.model.Pokemon
import javax.inject.Inject

class GetPokemons @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): List<Pokemon> {
        val pokemons = repository.getPokemonsFromApi()

        return if (pokemons.isNotEmpty()) {
            repository.clearPokemons()
            repository.insertPokemons(pokemons.map { it.toDatabase() })
            pokemons
        } else {
            repository.getPokemonsFromDatabase()
        }
    }
}