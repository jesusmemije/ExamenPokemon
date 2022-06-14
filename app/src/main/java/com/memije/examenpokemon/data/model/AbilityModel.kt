package com.memije.examenpokemon.data.model

data class AbilityModel(
    val ability: Name
) {
    data class Name(
        val name: String
    )
}