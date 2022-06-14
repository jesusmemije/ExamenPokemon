package com.memije.examenpokemon.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.memije.examenpokemon.R
import com.memije.examenpokemon.databinding.FragmentInfoPokemonBinding
import com.memije.examenpokemon.ui.viewmodel.InfoPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoPokemonFragment : Fragment() {

    private lateinit var _binding: FragmentInfoPokemonBinding
    private val binding get() = _binding

    private lateinit var pokemonName: String

    private val infoPokemonViewModel: InfoPokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonName = arguments?.getString("pokemon").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInfoPokemonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvName.text = pokemonName

        infoPokemonViewModel.onCreate(pokemonName)

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

        binding.btnAbilities.setOnClickListener {
            loadFragment(AbilitiesFragment(), pokemonName)
        }

        return root
    }

    private fun loadFragment(fragment: Fragment, pokemonName: String) {

        val bundle = Bundle()
        bundle.putString("pokemon", pokemonName)

        binding.btnAbilities.visibility = View.GONE
        binding.btnEvolutionaryLine.visibility = View.GONE

        fragment.arguments = bundle
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction?.commit()
    }
}
