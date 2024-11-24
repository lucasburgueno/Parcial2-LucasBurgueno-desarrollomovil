package com.example.myapplication

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Sprites(
    val front_default: String
)