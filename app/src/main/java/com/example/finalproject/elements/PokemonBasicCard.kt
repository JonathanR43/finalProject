package com.example.finalproject.elements

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

@Composable
fun PokemonCard(
    name: String? = null,
    imageUrl: String? = null,
)
{
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(165.dp)
            .background(color = Color(0xFF3F93D1))
            .padding(5.dp)
            .clickable
            {

            }
    )
    {
        if (imageUrl.isNullOrBlank()) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "Imagen local",
                modifier = Modifier.size(100.dp)
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Imagen de Pokémon",
                modifier = Modifier.size(100.dp),
                placeholder = painterResource(R.drawable.icon),
                error = painterResource(R.drawable.icon)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        if(name != null)
        {
            Text(
                name,
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
    var pokemonName by remember { mutableStateOf("Charizard") }

    PokemonCard(name = pokemonName)
}