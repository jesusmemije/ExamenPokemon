package com.memije.examenpokemon.data.network

import com.memije.examenpokemon.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val api: PokemonApiClient) {

    suspend fun getPokemons(): List<PokemonModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getPokemons()
            response.body()?.pList ?: emptyList()
        }
    }
}