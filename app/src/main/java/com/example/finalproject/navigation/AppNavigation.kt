package com.example.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject.screens.DetailScreen
import com.example.finalproject.screens.LoginScreen
import com.example.finalproject.screens.MainScreen
import com.example.finalproject.screens.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){
            LoginScreen(navController)
        }
        composable("signup"){
            SignUpScreen(navController)
        }
        composable("home") {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val name = currentUser?.displayName ?: "Entrenador"
            val mail = currentUser?.email ?: "Sin correo"

            MainScreen(
                navController = navController,
                username = name,
                email = mail
            )
        }
        composable(
            route = "detail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        )
        {
            backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: "Pikachu"

            val currentUser = FirebaseAuth.getInstance().currentUser
            val name = currentUser?.displayName ?: "Entrenador"
            val mail = currentUser?.email ?: "Sin correo"
            DetailScreen(
                navController = navController,
                username = name,
                email = mail,
                pokemonName = pokemonName
            )
        }
    }
}