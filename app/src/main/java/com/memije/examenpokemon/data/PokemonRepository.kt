package com.memije.examenpokemon.data

import com.memije.examenpokemon.data.database.dao.PokemonDao
import com.memije.examenpokemon.data.database.entities.PokemonEntity
import com.memije.examenpokemon.data.model.PokemonModel
import com.memije.examenpokemon.data.network.PokemonService
import com.memije.examenpokemon.domain.model.Pokemon
import com.memije.examenpokemon.domain.model.toDomain
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: PokemonService,
    private val pokemonDao: PokemonDao
) {

    suspend fun getPokemonsFromApi(): List<Pokemon> {
        val response: List<PokemonModel> = apiService.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabase(): List<Pokemon> {
        val response: List<PokemonEntity> = pokemonDao.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun insertPokemons(pokemons: List<PokemonEntity>) {
        pokemonDao.insertAll(pokemons)
    }

    suspend fun clearPokemons() {
        pokemonDao.deleteAll()
    }
}