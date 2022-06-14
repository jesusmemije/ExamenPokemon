package com.memije.examenpokemon.data.model

import com.google.gson.annotations.SerializedName

data class InfoPokemonModel(
    @SerializedName("base_happiness") val baseHappiness: Int,
    @SerializedName("capture_rate") val captureRate: Int,
    val color: Color,
    @SerializedName("egg_groups") val eggGroups: List<EggGroup>
) {
    data class Color(
        val name: String
    )

    data class EggGroup(
        val name: String
    )
}


