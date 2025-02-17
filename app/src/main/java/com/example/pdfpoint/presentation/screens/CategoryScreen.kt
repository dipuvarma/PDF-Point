package com.example.pdfpoint.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pdfpoint.presentation.comp.CategoryCardComp
import com.example.pdfpoint.presentation.navigation.AllBookByCategory
import com.example.pdfpoint.presentation.navigation.Category
import com.example.pdfpoint.presentation.viewModel.AppViewModel

@Composable
fun CategoryScreen(
    viewModel: AppViewModel,
    navController: NavController
) {

    /*Categories ui State*/
    val state = viewModel.bookCategoryState.collectAsState()
    val categoriesList = state.value.books ?: emptyList()

    when {
        state.value.isLoading -> {
            // Show loading indicator
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(text = "Loading...")
            }
        }

        state.value.error == null -> {
            // Show error message
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(text = "Error: ${state.value.error}")
            }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(categoriesList) {
                    CategoryCardComp(
                        categoryName = it.categoryName,
                        categoryImage = it.categoryImage,
                        onClick = { navController.navigate(AllBookByCategory(it.categoryName)) }
                    )
                }
            }
        }
    }
}