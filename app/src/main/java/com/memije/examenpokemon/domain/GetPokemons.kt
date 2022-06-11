package com.memije.examenpokemon.domain

import android.util.Log
import com.memije.examenpokemon.data.PokemonRepository
import com.memije.examenpokemon.data.database.entities.toDatabase
import com.memije.examenpokemon.domain.model.Pokemon
import javax.inject.Inject

class GetPokemons @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): List<Pokemon> {

        val pokemonsDB = repository.getPokemonsFromDatabase()

        return if (pokemonsDB.isEmpty()) {
            val pokemonsAPI = repository.getPokemonsFromApi()
            Log.d("DATA_RESULT", "Retorna los datos de la API: $pokemonsAPI")
            repository.clearPokemons()
            repository.insertPokemons(pokemonsAPI.map { it.toDatabase() })
            pokemonsAPI
        } else {
            Log.d("DATA_RESULT", "Retorna los datos de la DB: $pokemonsDB")
            pokemonsDB
        }

    }
}