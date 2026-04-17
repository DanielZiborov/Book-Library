package com.example.booklibrary.presentation.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booklibrary.domain.entities.BookEntity

@Composable
fun RefreshBooks (
    books: List<BookEntity>,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavController,
    onRefresh: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(books) { book ->
                BookCard(
                    bookInfo= book,
                    navController = navController
                )
            }
            item{ Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}