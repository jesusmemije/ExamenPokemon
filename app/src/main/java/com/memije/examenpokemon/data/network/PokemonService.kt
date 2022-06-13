package com.memije.examenpokemon.data.network

import android.util.Log
import com.memije.examenpokemon.data.model.InfoPokemonModel
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

    suspend fun getPokemon(name: String): InfoPokemonModel {
        return withContext(Dispatchers.IO) {
            api.getPokemon(name)
        }
    }
}