package com.example.finalproject.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.components.CustomButton
import com.example.finalproject.components.CustomInput
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

@Composable
fun SignUpScreen(navController: NavController)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var feedbackMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFE4D4D))
            .padding(horizontal = 49.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Spacer(modifier = Modifier.height(82.dp))

        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = "Imagen local",
            modifier = Modifier
                .height(75.dp)
                .width(137.dp),
        )

        Image(
            painter = painterResource(R.drawable.titulo),
            contentDescription = "Imagen local",
            modifier = Modifier
                .height(89.dp)
                .width(285.dp),
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            "Registrarse",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 32.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomInput(
            label = R.string.label_email,
            value = email,
            onValueChange = { email = it; isError = false },
            placeHolder = R.string.placeholder_email,
            isPassword = false
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomInput (
            label = R.string.label_password,
            value = password,
            onValueChange = { password = it; isError = false },
            placeHolder = R.string.placeholder_password,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomInput (
            label = R.string.label_confirm_password,
            value = confirmPassword,
            onValueChange = { confirmPassword = it; isError = false },
            placeHolder = R.string.placeholder_password,
            isPassword = true
        )
        
        Spacer(modifier = Modifier.height(50.dp))

        CustomButton(
            onClick = {
                when {
                    email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                        feedbackMessage = "Todos los campos son obligatorios"
                        isError = true
                        isSuccess = false
                    }
                    password != confirmPassword -> {
                        feedbackMessage = "Las contraseñas no coinciden"
                        isError = true
                        isSuccess = false
                    }
                    else -> {
                        isError = false
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    feedbackMessage = "¡Usuario registrado con éxito! Ya puedes dirigirte a Iniciar Sesión."
                                    isSuccess = true
                                    isError = false
                                } else {
                                    feedbackMessage = when (task.exception) {
                                        is FirebaseAuthUserCollisionException ->
                                            "Este correo ya está registrado."
                                        is FirebaseAuthWeakPasswordException ->
                                            "La contraseña es muy débil. Por favor, ingresa al menos 6 caracteres."
                                        is FirebaseAuthInvalidCredentialsException ->
                                            "El correo no es válido, asegurate de poner el formato correcto."
                                        else ->
                                            "Error al iniciar sesión"
                                    }
                                    isError = true
                                    isSuccess  = false
                                }
                            }
                    }
                }
            },
            text = R.string.button_signup
        )
        if (isError || isSuccess) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedbackMessage,
                color = if (isError) Color.Yellow else Color(0xFF00FFCC),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        )
        {
            Text(
                text = stringResource(R.string.signup_footer_account) + " ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.height(22.dp),
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = stringResource(R.string.signup_footer_login),
                color = Color(0XFF0453FF),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {navController.navigate("login")}
            )
        }
    }
}

@Preview (device = "spec:width=440dp,height=956dp,dpi=420")
@Composable
fun SignUpScreenPreview()
{
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}