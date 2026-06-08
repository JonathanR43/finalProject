package com.example.finalproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.finalproject.R
import com.example.finalproject.components.CustomBackButton
import com.example.finalproject.components.CustomProfileButton
import com.example.finalproject.components.ProfileDialog
import com.example.finalproject.components.StatRow
import com.example.finalproject.components.TypeCard
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DetailScreen(
    navController: NavController,
    username: String,
    email: String,
    pokemonName: String,
    viewModel: PokemonDetailViewModel = viewModel())
{
    var showProfile by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = pokemonName) {
        viewModel.fetchPokemonDetail(pokemonName)
    }

    val pokemon = viewModel.pokemonDetail
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFE4D4D))
            .padding(horizontal = 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(38.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            CustomBackButton(
                onClick = {navController.navigate("home")},
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Image(
                painterResource(R.drawable.titulo),
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

        Spacer(modifier = Modifier.height(123.dp))

        if(isLoading)
        {

        }
        else if(errorMessage != null)
        {

        }
        else if(pokemon != null)
        {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFEDEDED))
                    .wrapContentHeight()
            )
            {
                //Pokemon sprite
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-100).dp)
                        .size(209.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xFFFE4D4D))
                )
                {
                    val spriteUrl = pokemon.sprites.frontDefault
                    if (spriteUrl.isNullOrBlank()) {
                        Image(
                            painter = painterResource(R.drawable.icon),
                            contentDescription = "Imagen local",
                            modifier = Modifier.size(321.dp)
                        )
                    } else {
                        AsyncImage(
                            model = spriteUrl,
                            contentDescription = "Imagen de Pokémon",
                            modifier = Modifier.size(321.dp),
                            placeholder = painterResource(R.drawable.icon),
                            error = painterResource(R.drawable.icon)
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(120.dp))

                    //Pokemon name
                    if(pokemon.name != null)
                    {
                        Text(
                            pokemon.name.replaceFirstChar { it.uppercase() },
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 48.sp,
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
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(23.dp))

                    //Pokemon basic data
                    Row(
                        modifier = Modifier
                            .width(362.dp),
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Text(
                            "Height:",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .width(84.dp)
                        )

                        Text(
                            "${pokemon.height / 10.0} m",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .width(70.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            "Weight: ",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .width(84.dp)
                        )

                        Text(
                            "${pokemon.weight / 10.0} kg",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .width(95.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(23.dp))

                    //Pokemon types panel
                    Column(
                        modifier = Modifier
                            .width(362.dp)
                            .wrapContentHeight()
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            "Types",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                        )
                        {
                            pokemon.types.forEach { typeSlot ->
                                TypeCard(typeName = typeSlot.type.name)
                            }
                        }



                    }

                    Spacer(modifier = Modifier.height(23.dp))

                    //Pokemon stats panel
                    Column(
                        modifier = Modifier
                            .width(362.dp)
                            .wrapContentHeight()
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    )
                    {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            "Stats",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                        )
                        {
                            pokemon.stats.forEach{
                                statSlot ->
                                StatRow(statSlot.stat.name, statSlot.baseStat)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
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
fun DetailScreenPreview()
{
    /*
    val navController = rememberNavController()
    val pokemonDePrueba = SimplePokemon(
        name = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/25/"
    )
    DetailScreen(navController = navController, pokemonDePrueba)
    */
}