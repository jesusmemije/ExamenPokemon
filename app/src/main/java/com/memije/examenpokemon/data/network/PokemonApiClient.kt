package com.memije.examenpokemon.data.network

import com.memije.examenpokemon.data.model.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApiClient {
    @GET("pokemon?limit=151")
    suspend fun getPokemons(): Response<PokemonResponse>
}