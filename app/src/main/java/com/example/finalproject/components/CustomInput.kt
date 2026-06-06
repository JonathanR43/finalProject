package com.example.finalproject.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.R

@Composable
fun CustomInput(
    label: Int,
    value: String,
    placeHolder: Int,
    isPassword: Boolean = false,
    onValueChange: (String)-> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ){
        BasicText(
            text = stringResource(label),
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Color(0xFF333333),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation =
                if (isPassword){
                    PasswordVisualTransformation()
                } else{
                    VisualTransformation.None
                },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0XFFFFFFFF), Color(0XFFF1F1F1)),
                                start = Offset(0f, 0f),
                                end = Offset(Float.POSITIVE_INFINITY, 0f)
                            )
                        )
                        .border(2.dp, Color(0xFF8A2BE2).copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical= 14.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    if (value.isEmpty()) {
                        BasicText(
                            text = stringResource(placeHolder),
                            style = TextStyle(
                                color = Color(0xFF000000).copy(alpha = 0.5f),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun CustomInputPreview(){
    CustomInput( R.string.label_email,"", R.string.placeholder_email, onValueChange = {})
}