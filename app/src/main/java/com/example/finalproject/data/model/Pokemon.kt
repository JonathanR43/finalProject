package com.example.finalproject.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int, // La API lo devuelve en decímetros
    val weight: Int, // La API lo devuelve en hectogramos
    val sprites: PokemonSprites,
    val types: List<PokemonTypeSlot>,
    val stats: List<PokemonStatSlot>
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)

data class PokemonStatSlot(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String,
    val url: String
)