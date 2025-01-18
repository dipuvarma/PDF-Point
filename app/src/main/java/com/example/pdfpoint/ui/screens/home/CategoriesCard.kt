package com.example.pdfpoint.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage


@Composable
fun CategoriesCard(
    categoryTitle: String,
    categoryImage: String,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(180.dp)
            .clickable(onClick = { onClick.invoke() }),
        shape = RoundedCornerShape(8.dp), // Optional rounded corners for a polished look
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image with overlay
//            Image(
//                painter = painterResource(id = R.drawable.zendaya_4k),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//
//            )
            AsyncImage(
                model = categoryImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Semi-transparent overlay to enhance text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Adjust alpha for desired dimming
            )

            // Bold and Large Text
            Text(
                text = categoryTitle,
                style = TextStyle(
                    fontSize = 22.sp, // Large font size
                    fontWeight = FontWeight.Bold, // Bold font
                    color = Color.White // White text for contrast with dark overlay
                ),
                modifier = Modifier.align(Alignment.Center) // Center align the text
            )
        }
    }
}

