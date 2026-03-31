package com.example.booklibrary.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.booklibrary.domain.entities.Status
import com.example.booklibrary.presentation.state.BookFormState
import java.time.LocalDate

@Composable
fun BookForm(
    space: Dp = 10.dp,
    state: BookFormState,
    onNameChange: (String) -> Unit,
    onAuthorChange: (String) -> Unit,
    onYearChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onRatingChange: (Int) -> Unit,
    onStatusChange: (Status) -> Unit,
    onStartDateChange: (LocalDate?) -> Unit,
    onEndDateChange: (LocalDate?) -> Unit,
    bottomContent: @Composable () -> Unit,
    paddingValues: PaddingValues
) {

    val focusManager = LocalFocusManager.current

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space)
    ) {
        item {
            OutlinedTextField(
                value = state.nameOfBook,
                onValueChange = onNameChange,
                label = { Text(state.nameError.ifEmpty { "Book name" }) },
                modifier = Modifier.padding(
                    top = space,
                    start = space,
                    end = space
                ),
                isError = state.nameError.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }

        item {
            OutlinedTextField(
                value = state.author,
                onValueChange = onAuthorChange,
                label = { Text(state.authorError.ifEmpty { "Author" }) },
                modifier = Modifier.padding(
                    horizontal = space
                ),
                isError = state.authorError.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }

        item {
            OutlinedTextField(
                value = state.year,
                onValueChange = onYearChange,
                label = { Text(state.yearError.ifEmpty { "Year" }) },
                modifier = Modifier.padding(
                    horizontal = space
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                isError = state.yearError.isNotEmpty(),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }

        item {
            OutlinedTextField(
                value = state.description,
                onValueChange = onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier.padding(
                    horizontal = space
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }

        item {
            RatingBar(
                rating = state.rating,
                onRatingChange = onRatingChange
            )
        }

        item {
            StatusRadioButtons(
                status = state.status,
                onStatusChange = onStatusChange
            )
        }

        item {
            ReadingDatePicker(
                date = state.startDate,
                onDateChange = onStartDateChange,
                label = "Start date"
            )
        }

        item {
            ReadingDatePicker(
                date = state.endDate,
                onDateChange = onEndDateChange,
                label = "End date"
            )
        }

        item {
            if (state.dateReadingError.isNotEmpty()) {
                Text(
                    text = state.dateReadingError,
                    color = Color.Red
                )
            }
        }

        item {
            bottomContent()
        }
    }
}
