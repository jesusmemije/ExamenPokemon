package com.memije.examenpokemon.data.network

import com.memije.examenpokemon.data.model.InfoPokemonModel
import com.memije.examenpokemon.data.model.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {
    @GET("pokemon?limit=151")
    suspend fun getPokemons(): Response<PokemonResponse>

    @GET("pokemon-species/{name}")
    suspend fun getPokemon(@Path("name") name: String): InfoPokemonModel
}