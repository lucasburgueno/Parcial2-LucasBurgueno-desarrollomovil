package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Pokemon
}