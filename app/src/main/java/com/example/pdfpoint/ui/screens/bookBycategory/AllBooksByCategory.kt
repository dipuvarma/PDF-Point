package com.example.pdfpoint.ui.screens.bookBycategory

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
import com.example.pdfpoint.ui.PdfAppViewModel
import com.example.pdfpoint.ui.screens.allBooks.BooksCard

@Composable
fun AllBooksByCategory(category: String, viewModel: PdfAppViewModel) {

    val state = viewModel.getBooksByCategoryState.collectAsState()
    val data = state.value.books ?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getBooksByCategory(category)
    }
    when {
        state.value.isLoading -> {
            Text(text = "Loading")
        }

        state.value.error == null -> {
            Text(text = "Error")
        }

        state.value.books != null -> {
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
                        text = "All Books Of $category",
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
                            BooksCard(
                                imageUrl = it.BookImageUrl,
                                bookName = it.BookName,
                                bookAuthor = it.BookAuthor,
                                bookDescription = it.BookDescription,
                                bookUrl = it.BookUrl
                            )
                        }
                    }
                }
            }
        }
    }
}