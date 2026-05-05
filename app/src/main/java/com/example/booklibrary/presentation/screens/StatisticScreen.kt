package com.example.booklibrary.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booklibrary.common.components.CustomTopAppBar
import com.example.booklibrary.presentation.viewmodels.BooksViewModel

@Composable
fun StatisticScreen(
    booksViewModel: BooksViewModel
) {
    val booksUiState by booksViewModel.uiState.collectAsState()
    val statistic = booksUiState.statistic

    Scaffold(
        topBar = {
            CustomTopAppBar("Statistic")
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row{
                Text("Count of books: ")
                Text("${statistic.countOfBooks}")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row{
                Text("Mean time of reading: ")
                Text("${statistic.meanTimeOfReading}")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row{
                Text("Mean rating: ")
                Text("${statistic.meanRating}")
            }
        }
    }
}
