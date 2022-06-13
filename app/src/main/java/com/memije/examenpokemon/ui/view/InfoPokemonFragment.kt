package com.memije.examenpokemon.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.memije.examenpokemon.databinding.FragmentInfoPokemonBinding
import com.memije.examenpokemon.ui.viewmodel.InfoPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoPokemonFragment : Fragment() {

    private lateinit var _binding: FragmentInfoPokemonBinding

    private var pokemon: String? = null

    private val infoPokemonViewModel: InfoPokemonViewModel by viewModels()

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemon = arguments?.getString("pokemon")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInfoPokemonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvName.text = pokemon

        pokemon?.let { infoPokemonViewModel.onCreate(it) }

        var eggs = ""
        infoPokemonViewModel.pokemonInfo.observe(viewLifecycleOwner) { infoPokemonModel ->
            Log.d("InfoPokemonFragment", "Data infoPokemonModel: $infoPokemonModel")
            binding.tvBaseHappiness.text = "Felicidad base: " + infoPokemonModel.baseHappiness
            binding.tvCaptureRate.text = "Tasa de captura: " + infoPokemonModel.captureRate
            binding.tvColor.text = "Color: " + infoPokemonModel.color.name

            val lastIndex = infoPokemonModel.eggGroups.lastIndex
            infoPokemonModel.eggGroups.forEachIndexed() { index, eggGroup ->
                eggs += if (index == lastIndex) {
                    eggGroup.name
                } else {
                    eggGroup.name + ", "
                }
            }
            binding.tvEggGroups.text = "Grupo de huevos: $eggs"
        }

        infoPokemonViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbLoading.isVisible = it
        }

        return root
    }
}