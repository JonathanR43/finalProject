package com.example.finalproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.finalproject.R
import com.example.finalproject.data.model.SimplePokemon
import retrofit2.Retrofit

@Composable
fun PokemonCard(
    pokemon: SimplePokemon
)
{
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(165.dp)
            .background(color = Color.White)
            .padding(5.dp)
            .clickable
            {

            }
    )
    {
        if (pokemon.frontSprite.isNullOrBlank()) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "Imagen local",
                modifier = Modifier.size(100.dp)
            )
        } else {
            AsyncImage(
                model = pokemon.frontSprite,
                contentDescription = "Imagen de Pokémon",
                modifier = Modifier.size(100.dp),
                placeholder = painterResource(R.drawable.icon),
                error = painterResource(R.drawable.icon)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        if(pokemon.name != null)
        {
            Text(
                pokemon.name,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        else
        {
            Text(
                "Not Found",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true,
         device = "spec:width=440dp,height=956dp,dpi=420"
        )
@Composable
fun PokeCardPreview() {
    val pokemonDePrueba = SimplePokemon(
        name = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/25/"
    )

    PokemonCard(pokemon = pokemonDePrueba)
}