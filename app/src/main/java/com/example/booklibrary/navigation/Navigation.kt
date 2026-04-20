package com.example.booklibrary.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import com.example.booklibrary.presentation.viewmodels.BooksViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booklibrary.presentation.screens.AddBookScreen
import com.example.booklibrary.presentation.screens.BookListScreen
import com.example.booklibrary.presentation.screens.DetailScreen
import com.example.booklibrary.presentation.viewmodels.BookFormViewModel
import java.util.UUID

@Composable
fun Navigation() {

    val navController = rememberNavController()

    val activity = LocalActivity.current as? ComponentActivity

    NavHost(
        navController = navController,
        startDestination = Destination.BookList.route
    ) {

        composable(Destination.BookList.route) {
            BookListScreen(
                booksViewModel = hiltViewModel<BooksViewModel>(activity!!),
                navController = navController
            )
        }

        composable(Destination.Add.route) {
            AddBookScreen(
                booksViewModel = hiltViewModel<BooksViewModel>(activity!!),
                formViewModel = viewModel<BookFormViewModel>(),
                navController = navController
            )
        }

        composable(
            route = Destination.Details.route,
            arguments = listOf(
                navArgument(Destination.BOOK_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Destination.BOOK_ID)?.let {
                val bookId = UUID.fromString(it)
                DetailScreen(
                    bookId = bookId,
                    navController = navController,
                    booksViewModel = hiltViewModel<BooksViewModel>(activity!!),
                    formViewModel = viewModel<BookFormViewModel>()
                )
            }
        }
    }
}
