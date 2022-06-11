package com.memije.examenpokemon.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memije.examenpokemon.domain.GetPokemons
import com.memije.examenpokemon.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemons: GetPokemons
) : ViewModel() {

    val pokemonList = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pList = getPokemons()
            if (pList.isNotEmpty()) {
                pokemonList.postValue(pList)
            }
            isLoading.postValue(false)
        }
    }
}