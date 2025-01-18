package com.example.pdfpoint.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pdfpoint.ui.PdfAppViewModel
import com.example.pdfpoint.ui.navigation.AllBooksByCategoryScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreenUI(
    viewModel: PdfAppViewModel,
    navController: NavController
) {

    val uiState = viewModel.getAllCategoryState.collectAsState()
    val data = uiState.value.books ?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategory()
    }

    when {
        uiState.value.isLoading -> {
            Text(text = "Loading")
        }

        uiState.value.error == null -> {
            Text(text = "Error")
        }

        uiState.value.books != null -> {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = "Books Categories",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp)
                    ) {
                        items(data) {
                            CategoriesCard(
                                categoryImage = it.CategoryImageUrl,
                                categoryTitle = it.CategoryName,
                                onClick = {navController.navigate(AllBooksByCategoryScreen(category = it.CategoryName))}
                            )
                        }
                    }
                }
            }
        }
    }
}






