package com.example.finalproject.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val results: List<SimplePokemon>
)

data class SimplePokemon(
    val name: String,
    val url: String
){
    val frontSprite: String
        get() {
            val id = url.trimEnd('/').split('/').lastOrNull() ?: ""
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        }
}