package com.example.booklibrary.presentation.screens

import com.example.booklibrary.presentation.viewmodels.BooksViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booklibrary.common.components.CustomTopAppBar
import com.example.booklibrary.presentation.viewmodels.BookFormViewModel
import com.example.booklibrary.presentation.views.BookForm
import com.example.booklibrary.utils.showConfirm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(
    booksViewModel: BooksViewModel,
    formViewModel: BookFormViewModel,
    navController: NavController
) {
    val state = formViewModel.state

    val snackbarHostState = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CustomTopAppBar("Add new book")
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
                Button(
                    onClick = {
                        showConfirm(
                            message = "Are you sure you want add this?",
                            coroutineScope = coroutineScope,
                            snackbarHostState = snackbarHostState,
                        ) {
                            booksViewModel.addBook(
                                nameOfBook = state.nameOfBook,
                                year = state.year.toInt(),
                                description = state.description,
                                author = state.author,
                                rating = state.rating,
                                status = state.status,
                                startDate = state.startDate,
                                endDate = state.endDate
                            )
                            navController.popBackStack()
                        }
                    },
                    enabled = formViewModel.isValid,
                    modifier = Modifier.padding(bottom = 10.dp),
                ) {
                    Text("Save")
                }
            }
        )
    }
}
