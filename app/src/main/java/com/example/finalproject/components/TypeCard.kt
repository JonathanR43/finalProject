package com.example.finalproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.finalproject.R
import com.example.finalproject.data.model.SimplePokemon

@Composable
fun TypeCard(
    typeName: String
)
{
    val launchTypeIconMap = mapOf(
        "bug" to R.drawable.bug_icon,
        "dark" to R.drawable.dark_icon,
        "dragon" to R.drawable.dragon_icon,
        "electric" to R.drawable.electric_icon,
        "fairy" to R.drawable.fairy_icon,
        "fighting" to R.drawable.fighting_icon,
        "fire" to R.drawable.fire_icon,
        "flying" to R.drawable.flying_icon,
        "ghost" to R.drawable.ghost_icon,
        "grass" to R.drawable.grass_icon,
        "ground" to R.drawable.ground_icon,
        "ice" to R.drawable.ice_icon,
        "normal" to R.drawable.normal_icon,
        "poison" to R.drawable.poison_icon,
        "psychic" to R.drawable.psychic_icon,
        "rock" to R.drawable.rock_icon,
        "steel" to R.drawable.rock_icon,
        "water" to R.drawable.water_icon
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .width(88.dp)
            .height(105.dp)
            .background(color = Color.White)
            .padding(5.dp)
    )
    {
        val imageResId = launchTypeIconMap[typeName.lowercase()] ?: R.drawable.icon

        Image(
            painter = painterResource(imageResId),
            contentDescription = "Imagen local",
            modifier = Modifier.size(65.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            typeName,
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

@Preview(showBackground = false,
    device = "spec:width=440dp,height=956dp,dpi=420"
)
@Composable
fun TypeCardPreview() {
    val pokemonDePrueba = SimplePokemon(
        name = "Normal",
        url = "https://pokeapi.co/api/v2/pokemon/25/"
    )

    TypeCard("fire")
}