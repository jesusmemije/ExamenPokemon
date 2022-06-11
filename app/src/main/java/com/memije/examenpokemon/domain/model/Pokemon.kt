package com.memije.examenpokemon.domain.model

import com.memije.examenpokemon.data.database.entities.PokemonEntity
import com.memije.examenpokemon.data.model.PokemonModel

data class Pokemon(val name: String, val url: String)

fun PokemonModel.toDomain() = Pokemon(name, url)
fun PokemonEntity.toDomain() = Pokemon(name, url)