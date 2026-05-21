package com.example.finalproject.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
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
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.finalproject.R

@Composable
fun PokemonCardVisuals(
    imageUrl: String? = null,
    localImageRes: Int? = null
)
{
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .width(180.dp)
            .height(180.dp)
            .background(color = Color(0xFF3F93D1))
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        if(localImageRes != null)
        {
            //Imagen local (El preview no muestra imagenes con url)
            Image(
                painter = painterResource(id = localImageRes),
                contentDescription = "Imagen local",
                modifier = Modifier
                    .height(100.dp)
                    .width(200.dp)
            )
        }
        else if(imageUrl != null)
        {
            //Imagen con url
            AsyncImage(
                model = imageUrl,
                contentDescription = "Imagen de PÃ³kemon",
                modifier = Modifier
                    .weight(0.5f)
            )
        }

        Spacer(modifier = Modifier
                    .height(15.dp)
        )

        Text(
            "Charizard",
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

@Preview(showBackground = true,
         device = "spec:width=402dp,height=874dp,dpi=420"
        )
@Composable
fun PokeCardPreview() {
    PokemonCardVisuals(localImageRes = R.drawable.icon)
}