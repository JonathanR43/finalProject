package com.example.finalproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.components.CustomProfileButton
import com.example.finalproject.components.CustomSearchBar
import com.example.finalproject.components.PokemonCard
import com.example.finalproject.components.ProfileDialog
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainScreen(
    navController: NavController,
    username: String,
    email: String,
    viewModel: PokemonViewModel = viewModel()
)
{
    var showProfile by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true)
    {
        viewModel.fetchPokemon()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFE4D4D))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(38.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.titulo),
                contentDescription = "Imagen Local",
                modifier = Modifier
                    .height(68.dp)
                    .width(220.dp)
            )

            CustomProfileButton(
                onClick = { showProfile = true },
                modifier = Modifier.align(Alignment.CenterEnd)
            )

        }

        Spacer(modifier = Modifier.height(28.dp))

        CustomSearchBar(
            viewModel.searchText,
            onValueChange = { newSeatch -> viewModel.searchText = newSeatch }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val pokemonList = viewModel.filteredPokemonList

            items(pokemonList)
            {
                pokemon ->
                PokemonCard(pokemon = pokemon, onClick = {navController.navigate("detail/${pokemon.name}")})

            }
        }
    }
    if (showProfile) {
        ProfileDialog(
            onDismiss = { showProfile = false },
            username = username,
            email = email,
            onLogout = {
                showProfile = false
                FirebaseAuth.getInstance().signOut()
                navController.navigate("login") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            }
        )
    }

}

@Preview (device = "spec:width=440dp,height=956dp,dpi=420")
@Composable
fun MainScreenPreview()
{
    val navController = rememberNavController()
    MainScreen(
        navController = navController,
        username = "Entrenador de Prueba",
        email = "preview@pokemon.com"
    )
}