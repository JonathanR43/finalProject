package com.example.finalproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

@Composable
fun LoginScreen(navController: NavController)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var messageError by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

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
            contentDescription = "Imagen logo",
            modifier = Modifier
                .height(75.dp)
                .width(137.dp),
        )

        Image(
            painter = painterResource(R.drawable.titulo),
            contentDescription = "Imagen titulo",
            modifier = Modifier
                .height(89.dp)
                .width(285.dp),
        )

        Spacer(modifier = Modifier.height(96.dp))

        Text(
            "Iniciar Sesión",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 32.sp
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomInput(
            label = R.string.label_email,
            value = email,
            onValueChange = { email = it; isError = false },
            placeHolder = R.string.placeholder_email,
            isPassword = false
        )

        Spacer(modifier = Modifier.height(19.dp))

        CustomInput (
            label = R.string.label_password,
            value = password,
            onValueChange = { password = it; isError = false },
            placeHolder = R.string.placeholder_password,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(50.dp))

        CustomButton(
            onClick = {
                if(email.isEmpty() || password.isEmpty()){
                    messageError = "Todos los campos son obligatorios"
                    isError = true
                }
                else{
                    isError = false
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                isError = false
                                navController.navigate("home")
                            } else {
                                messageError = when (val exception= task.exception) {

                                    is FirebaseAuthInvalidCredentialsException ->
                                        "La información de inicio de sesión es incorrecta"
                                    is FirebaseAuthInvalidUserException -> {
                                        when (exception.errorCode) {
                                            "ERROR_USER_DISABLED" ->
                                                "La cuenta fue deshabilitada, por favor ponte en contacto con un administrador."
                                            else ->
                                                "La información de inicio de sesión es incorrecta"
                                        }
                                    }
                                    is FirebaseTooManyRequestsException ->
                                        "Demasiados intentos. Intenta más tarde"
                                    else ->
                                        "Error al iniciar sesión"
                                }
                                isError = true
                            }
                        }
                }
            },
            text = R.string.button_login
        )
        if (isError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = messageError,
                color = Color.Yellow,
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
                text = stringResource(R.string.login_footer_no_account) + " ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.height(22.dp),
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = stringResource(R.string.login_footer_register),
                color = Color(0XFF0453FF),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {navController.navigate("signup")}
            )
        }
    }
}

@Preview (device = "spec:width=440dp,height=956dp,dpi=420")
@Composable
fun LoginScreenPreview()
{
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}