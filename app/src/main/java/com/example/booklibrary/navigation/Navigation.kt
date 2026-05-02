package com.example.booklibrary.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.example.booklibrary.presentation.screens.SettingsScreen
import com.example.booklibrary.presentation.screens.StatisticScreen
import com.example.booklibrary.presentation.viewmodels.BookFormViewModel
import com.example.booklibrary.presentation.viewmodels.BookNotificationViewModel
import java.util.UUID

@Composable
fun Navigation() {

    val navController = rememberNavController()

    val activity = LocalActivity.current as? ComponentActivity

    NavHost(
        navController = navController,
        startDestination = Destination.BookList.route,
        enterTransition = { NavAnimations.fadeEnter() },
        exitTransition = { NavAnimations.fadeExit() }
    ) {

        composable(Destination.BookList.route) {
            BookListScreen(
                booksViewModel = hiltViewModel<BooksViewModel>(activity!!),
                navController = navController
            )
        }

        composable(
            Destination.Add.route,
            enterTransition = { NavAnimations.run { verticalEnter() } },
            exitTransition = { NavAnimations.run { verticalExit() } }
        ) {
            AddBookScreen(
                booksViewModel = hiltViewModel<BooksViewModel>(activity!!),
                formViewModel = viewModel<BookFormViewModel>(),
                navController = navController
            )
        }

        composable(
            route = Destination.Details.route,
            enterTransition = { NavAnimations.run { horizontalEnter() } },
            exitTransition = { NavAnimations.run { horizontalExit() } },
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

        composable(
            Destination.Statistic.route,
            enterTransition = { fadeIn(tween(600)) },
            exitTransition = { fadeOut(tween(600)) }
        ) {
            StatisticScreen(
                booksViewModel = hiltViewModel<BooksViewModel>(activity!!)
            )
        }

        composable(
            Destination.Settings.route,
            enterTransition = { NavAnimations.run { horizontalEnter() } },
            exitTransition = { NavAnimations.run { horizontalExit() } }
        ) {
            SettingsScreen(
                bookNotificationViewModel = hiltViewModel<BookNotificationViewModel>()
            )
        }
    }
}
