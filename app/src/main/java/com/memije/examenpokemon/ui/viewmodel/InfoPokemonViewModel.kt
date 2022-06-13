package com.memije.examenpokemon.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memije.examenpokemon.data.model.InfoPokemonModel
import com.memije.examenpokemon.domain.GetPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoPokemonViewModel @Inject constructor(
    private val getPokemon: GetPokemon
) : ViewModel() {

    val pokemonInfo = MutableLiveData<InfoPokemonModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pInfo = getPokemon.invoke(name)
            pokemonInfo.postValue(pInfo)
            isLoading.postValue(false)
        }
    }
}