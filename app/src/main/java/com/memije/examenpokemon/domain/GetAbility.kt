package com.memije.examenpokemon.domain

import com.memije.examenpokemon.data.PokemonRepository
import com.memije.examenpokemon.data.model.AbilityModel
import javax.inject.Inject

class GetAbility @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(name: String): List<AbilityModel> {
        return repository.getAbilityFromApi(name)
    }
}