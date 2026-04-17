package com.example.booklibrary.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.booklibrary.common.components.CustomTopAppBar
import com.example.booklibrary.navigation.Destination
import com.example.booklibrary.presentation.viewmodels.BooksViewModel
import com.example.booklibrary.presentation.views.RefreshBooks
import com.example.booklibrary.utils.showConfirm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BookListScreen(
    booksViewModel: BooksViewModel,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val books by booksViewModel.booksState.collectAsState()

    val isLoading by booksViewModel.isLoading
    val isRefreshing by booksViewModel.isRefreshing


//    if (error.isNotEmpty()) {
//        LaunchedEffect(error) {
//            showConfirm(
//                message = error,
//                coroutineScope = this,
//                snackbarHostState = snackbarHostState,
//                withDismissAction = true,
//                actionLabel = "",
//                consentAction = {}
//            )
//        }
//    }
    LaunchedEffect(Unit) {
        booksViewModel.uiEvent.collect {
            showConfirm(
                message = it,
                coroutineScope = this,
                snackbarHostState = snackbarHostState,
                withDismissAction = true,
                actionLabel = "",
                consentAction = {}
            )
        }
    }

    val configuration = LocalConfiguration.current
    val rightPadding =
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 32.dp else 0.dp

    Scaffold(
        topBar = { CustomTopAppBar("Books Library") },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        floatingActionButton = {
            if (!isLoading) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(
                            route = Destination.Add.route
                        ) {
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(
                        end = rightPadding
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Book")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { paddingValues ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            RefreshBooks(
                books = books,
                isRefreshing = isRefreshing,
                paddingValues = paddingValues,
                navController = navController
            ) {
                booksViewModel.refreshBooks()
            }
        }
    }
}
