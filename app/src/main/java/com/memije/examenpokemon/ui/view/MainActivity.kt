package com.memije.examenpokemon.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.memije.examenpokemon.R
import com.memije.examenpokemon.adapter.PokemonAdapter
import com.memije.examenpokemon.databinding.ActivityMainBinding
import com.memije.examenpokemon.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PokemonAdapter.OnPokemonClickListener {

    private lateinit var binding: ActivityMainBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    private val adapter = PokemonAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonViewModel.onCreate()

        // set recyclerview adapter
        binding.recyclerview.adapter = adapter

        pokemonViewModel.pokemonList.observe(this) { pokemonList ->
            Log.d("MainActivity", "pokemonList: $pokemonList")
            adapter.setPokemonList(pokemonList)
        }

        pokemonViewModel.isLoading.observe(this) {
            binding.pbLoading.isVisible = it
        }
    }

    override fun onItemClick(name: String) {
        val bundle = Bundle()
        bundle.putString("pokemon", name)

        val transaction = supportFragmentManager.beginTransaction()

        val fragment = InfoPokemonFragment()
        fragment.arguments = bundle

        transaction.replace(R.id.cl_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}