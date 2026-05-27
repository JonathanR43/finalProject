package com.example.finalproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.components.CustomSearchBar
import com.example.finalproject.components.PokemonCard

@Composable
fun MainScreen(navController: NavController)
{
    var pokemonList by remember {
        mutableStateOf(listOf<Pair<String, String>>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFE4D4D))
            .padding(horizontal = 49.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(38.dp))

        Image(
            painterResource(R.drawable.titulo),
            contentDescription = "Imagen Local",
            modifier = Modifier
                .height(68.dp)
                .width(220.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        CustomSearchBar("") { }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList)
            {
                pokemonCard ->
                PokemonCard(name = pokemonCard.first, imageUrl = pokemonCard.second)

            }
        }

    }
}

@Preview (device = "spec:width=440dp,height=956dp,dpi=420")
@Composable
fun MainScreenPreview()
{
    val navController = rememberNavController()
    MainScreen(navController = navController)
}