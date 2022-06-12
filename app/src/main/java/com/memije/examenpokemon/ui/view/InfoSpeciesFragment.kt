package com.memije.examenpokemon.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memije.examenpokemon.databinding.FragmentInfoSpeciesBinding

class InfoSpeciesFragment : Fragment() {

    private lateinit var _binding: FragmentInfoSpeciesBinding

    private var pokemon: String? = null

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
        _binding = FragmentInfoSpeciesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvName.text = pokemon

        return root
    }
}