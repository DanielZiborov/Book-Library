package com.example.booklibrary.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun SortMenu(
    onSort: (String) -> Unit,
    onOff: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Sort,
                contentDescription = "Sort"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Rating") },
                onClick = {
                    onSort("RATING")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Year") },
                onClick = {
                    onSort("YEAR")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Author") },
                onClick = {
                    onSort("AUTHOR")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Reset") },
                onClick = {
                    onOff()
                    expanded = false
                }
            )
        }
    }
}