package com.memije.examenpokemon.domain

import com.memije.examenpokemon.data.PokemonRepository
import com.memije.examenpokemon.data.model.InfoPokemonModel
import javax.inject.Inject

class GetPokemon @Inject constructor(
    private val repository: PokemonRepository,
) {

    suspend operator fun invoke(name: String): InfoPokemonModel {
        return repository.getPokemonFromApi(name)
    }
}