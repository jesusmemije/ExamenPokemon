package com.memije.examenpokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memije.examenpokemon.data.model.AbilityModel
import com.memije.examenpokemon.databinding.AbilityItemBinding

class AbilityAdapter: RecyclerView.Adapter<AbilityViewHolder>() {

    private var abilities = mutableListOf<AbilityModel>()

    fun setAbilityList(abilities: List<AbilityModel>) {
        this.abilities = abilities.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AbilityItemBinding.inflate(inflater, parent, false)
        return AbilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val ability = abilities[position]
        holder.binding.tvAbility.text = ability.ability.name
    }

    override fun getItemCount(): Int {
        return abilities.size
    }
}

class AbilityViewHolder(val binding: AbilityItemBinding) : RecyclerView.ViewHolder(binding.root) {}