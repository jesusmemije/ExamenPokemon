package com.memije.examenpokemon.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.memije.examenpokemon.data.database.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    suspend fun getPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()
}