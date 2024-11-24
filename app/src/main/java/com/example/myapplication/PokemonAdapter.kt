package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemPokemonBinding


class PokemonAdapter(
    private val onItemClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(PokemonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(getItem(adapterPosition))
            }
        }

        fun bind(pokemon: Pokemon) {
            binding.tvName.text = pokemon.name
            Glide.with(itemView)
                .load(pokemon.sprites.front_default)
                .into(binding.ivPokemon)
        }
    }

    private class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) =
            oldItem == newItem
    }
}
