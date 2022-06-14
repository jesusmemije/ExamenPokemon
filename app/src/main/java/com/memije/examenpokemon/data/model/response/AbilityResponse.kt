package com.memije.examenpokemon.data.model.response

import com.google.gson.annotations.SerializedName
import com.memije.examenpokemon.data.model.AbilityModel

data class AbilityResponse(
    @SerializedName("abilities") val aList: List<AbilityModel>
)