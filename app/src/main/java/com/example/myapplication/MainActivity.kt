package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import com.example.myapplication.PokemonAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PokemonAdapter { pokemon ->  // Cambié a PokemonAdapter sin el prefijo extra
        navigateToDetail(pokemon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        loadPokemons()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun loadPokemons() {
        lifecycleScope.launch {
            try {
                val pokemonList = (1..20).map {
                    RetrofitClient.apiService.getPokemon(it)
                }
                adapter.submitList(pokemonList)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToDetail(pokemon: Pokemon) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("id", pokemon.id)
            putExtra("name", pokemon.name)
            putExtra("height", pokemon.height)
            putExtra("weight", pokemon.weight)
            putExtra("image", pokemon.sprites.front_default)
        }
        startActivity(intent)
    }
}
