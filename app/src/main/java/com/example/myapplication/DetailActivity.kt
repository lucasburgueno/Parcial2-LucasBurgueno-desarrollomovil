package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            tvName.text = "Nombre: ${intent.getStringExtra("name")}"
            tvId.text = "ID: ${intent.getIntExtra("id", 0)}"
            tvHeight.text = "Altura: ${intent.getIntExtra("height", 0)}"
            tvWeight.text = "Peso: ${intent.getIntExtra("weight", 0)}"

            Glide.with(this@DetailActivity)
                .load(intent.getStringExtra("image"))
                .into(ivPokemon)
        }
    }
}