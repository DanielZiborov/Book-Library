package com.example.booklibrary.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
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
import com.example.booklibrary.domain.entities.Status

@Composable
fun FilterMenu(
    onFilter: (Status) -> Unit,
    onOff: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Filter"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Status.entries.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status.message) },
                    onClick = {
                        onFilter(status)
                        expanded = false
                    }
                )
            }
            DropdownMenuItem(
                text = { Text("Show all") },
                onClick = {
                    onOff()
                    expanded = false
                }
            )
        }
    }
}