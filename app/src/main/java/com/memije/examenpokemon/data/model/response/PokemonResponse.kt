package com.memije.examenpokemon.data.model.response

import com.google.gson.annotations.SerializedName
import com.memije.examenpokemon.data.model.PokemonModel

data class PokemonResponse(
    @SerializedName("results") val pList: List<PokemonModel>
)