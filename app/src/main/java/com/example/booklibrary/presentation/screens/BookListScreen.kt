package com.example.booklibrary.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booklibrary.presentation.views.BookCard
import com.example.booklibrary.common.components.CustomTopAppBar
import com.example.booklibrary.navigation.Destination
import com.example.booklibrary.presentation.viewmodels.BooksViewModel

@Composable
fun BookListScreen(
    booksViewModel: BooksViewModel,
    navController: NavController
) {
    val books by booksViewModel.booksState.collectAsState()

    val configuration = LocalConfiguration.current
    val rightPadding = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 32.dp else 0.dp

    Scaffold(
        topBar = {CustomTopAppBar("Books Library")},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        route = Destination.Add.route
                    ){
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.padding(
                    end = rightPadding
                )
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Book")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            items(books.size) { index ->
                BookCard(
                    bookInfo= books[index],
                    navController = navController
                )
            }
            item{ Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}
