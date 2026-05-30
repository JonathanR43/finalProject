package com.example.finalproject.screens

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.model.SimplePokemon
import com.example.finalproject.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    var pokemonList by mutableStateOf(listOf<SimplePokemon>())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchPokemon() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try
            {
                val response = RetrofitInstance.apiService.getPokemonList()
                pokemonList = response.results
            }
            catch (e: Exception)
            {
                errorMessage = "Error al cargar los Pokémon: ${e.localizedMessage}"
            }
            finally
            {
                isLoading = false
            }
        }
    }
}