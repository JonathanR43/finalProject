package com.example.finalproject.screens

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.model.PokemonDetail
import com.example.finalproject.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class PokemonDetailViewModel : ViewModel() {

    var pokemonDetail by mutableStateOf<PokemonDetail?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.apiService.getPokemonDetail(name.lowercase())
                pokemonDetail = response
            } catch (e: Exception) {
                errorMessage = "Error al cargar los detalles: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}