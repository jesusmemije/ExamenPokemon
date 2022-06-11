package com.memije.examenpokemon.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.memije.examenpokemon.domain.model.Pokemon

@Entity(tableName = "pokemon_table")
class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
)

fun Pokemon.toDatabase() = PokemonEntity(name = name, url = url)