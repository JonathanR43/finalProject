package com.example.finalproject.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileDialog(
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
    username: String,
    email: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            BasicText(
                text = "My Profile",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Column {
                BasicText(
                    text = "Username:",
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                BasicText(
                    text = username,
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                BasicText(
                    text = "Email:",
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                BasicText(
                    text = email,
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                TextButton(onClick = onDismiss) {
                    BasicText(
                        text = "Close",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                CustomLogoutButton(
                    onClick = onLogout
                )
            }
        }
    )
}


@Preview(device = "spec:width=440dp,height=956dp,dpi=420", showSystemUi = true)
@Composable
fun ProfileDialogPreview()
{
    ProfileDialog( onDismiss = {}, onLogout = {},"Brayan", "Brayan1@gmail.com")
}