package com.example.finalproject.data.network

import com.example.finalproject.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        //How many pokemons bring at the start
        @Query("limit") limit: Int = 30,
    ): PokemonListResponse
}