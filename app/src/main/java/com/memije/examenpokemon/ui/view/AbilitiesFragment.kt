package com.memije.examenpokemon.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.memije.examenpokemon.adapter.AbilityAdapter
import com.memije.examenpokemon.databinding.FragmentAbilitiesBinding
import com.memije.examenpokemon.ui.viewmodel.AbilityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilitiesFragment : Fragment() {

    private lateinit var _binding: FragmentAbilitiesBinding
    private val binding get() = _binding

    private lateinit var pokemonName: String

    private val abilityViewModel: AbilityViewModel by viewModels()

    private val adapter = AbilityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonName = arguments?.getString("pokemon").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAbilitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        abilityViewModel.onCreate(pokemonName)

        // set recyclerview adapter
        binding.recyclerview.adapter = adapter

        abilityViewModel.abilityList.observe(viewLifecycleOwner) { abilityList ->
            Log.d("AbilitiesFragment", "abilityList: $abilityList")
            adapter.setAbilityList(abilityList)
        }

        abilityViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbLoading.isVisible = it
        }

        return root
    }
}