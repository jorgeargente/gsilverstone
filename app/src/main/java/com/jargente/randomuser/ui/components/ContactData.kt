package com.jargente.randomuser.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ContactData(icon: Painter, title: String, data: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Icon(
            painter = icon, contentDescription = "", modifier = Modifier.padding(10.dp)
        )

        Column(
            modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 8.dp)
        ) {
            Text(
                text = title, color = Color.Gray
            )

            Text(
                text = data, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 6.dp)
            )
        }
    }

}