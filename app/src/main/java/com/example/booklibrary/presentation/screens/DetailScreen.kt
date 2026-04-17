package com.example.booklibrary.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.booklibrary.common.components.CustomTopAppBar
import com.example.booklibrary.presentation.viewmodels.BookFormViewModel
import com.example.booklibrary.presentation.viewmodels.BooksViewModel
import com.example.booklibrary.presentation.views.BookForm
import com.example.booklibrary.utils.showConfirm
import java.util.UUID

@Composable
fun DetailScreen(
    bookId: UUID,
    navController: NavController,
    booksViewModel: BooksViewModel,
    formViewModel: BookFormViewModel = viewModel(),
) {
    val state = formViewModel.state

    val books by booksViewModel.booksState.collectAsState()
    val book = books.find { it.id == bookId }

    val snackbarHostState = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(book) {
        formViewModel.setInitial(book)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(book?.title ?: "No book")
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { paddingValues ->
        BookForm(
            paddingValues = paddingValues,
            state = state,
            onNameChange = formViewModel::onNameChange,
            onAuthorChange = formViewModel::onAuthorChange,
            onYearChange = formViewModel::onYearChange,
            onDescriptionChange = formViewModel::onDescriptionChange,
            onRatingChange = formViewModel::onRatingChange,
            onStatusChange = formViewModel::onStatusChange,
            onStartDateChange = formViewModel::onStartDateChange,
            onEndDateChange = formViewModel::onEndDateChange,
            bottomContent = {
                Column {
                    Button(
                        onClick = {
                            showConfirm(
                                message = "Are you sure you want delete this?",
                                coroutineScope = coroutineScope,
                                snackbarHostState = snackbarHostState,
                            ) {
                                booksViewModel.deleteBook(bookId)
                                navController.popBackStack()
                            }
                        },
                        enabled = formViewModel.isValid
                    ) {
                        Text("Delete")
                    }
                    Button(
                        onClick = {
                            showConfirm(
                                message = "Are you sure you want redact this?",
                                coroutineScope = coroutineScope,
                                snackbarHostState = snackbarHostState,
                            ) {
                                booksViewModel.redactionBook(
                                    id = bookId,
                                    nameOfBook = state.nameOfBook,
                                    author = state.author,
                                    year = state.year.toInt(),
                                    description = state.description,
                                    rating = state.rating,
                                    status = state.status,
                                    startDate = state.startDate,
                                    endDate = state.endDate,
                                )
                            }
                        },
                        enabled = formViewModel.isValid,
                        modifier = Modifier.padding(
                            vertical = 10.dp
                        )
                    ) {
                        Text("Redact")
                    }
                }
            }
        )
    }
}
