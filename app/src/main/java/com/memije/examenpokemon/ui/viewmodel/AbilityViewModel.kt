package com.memije.examenpokemon.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memije.examenpokemon.data.model.AbilityModel
import com.memije.examenpokemon.domain.GetAbility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel @Inject constructor(
    private val getAbility: GetAbility
) : ViewModel() {

    val abilityList = MutableLiveData<List<AbilityModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val aList = getAbility(name)
            if (aList.isNotEmpty()) {
                abilityList.postValue(aList)
            }
            isLoading.postValue(false)
        }
    }
}