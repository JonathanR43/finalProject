package com.example.finalproject.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatRow(statName: String, statValue: Int)
{
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    {
        Text(
            statName,
            textAlign = TextAlign.Left,
            style = TextStyle(
                color = Color.Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .weight(1f)
        )

        Text(
            statValue.toString(),
            textAlign = TextAlign.End,
            style = TextStyle(
                color = Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .width(70.dp)
        )
    }
}

@Preview
@Composable
fun StatRotPreview()
{
    StatRow("Special-attack", 500)
}