package com.example.booklibrary.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booklibrary.domain.entities.Status

@Composable
fun StatusRadioButtons(
    status: Status,
    onStatusChange: (Status) -> Unit
) {
    Column {
        Status.entries.forEach { stat ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = status == stat,
                    onClick = { onStatusChange(stat) }
                )
                Spacer(Modifier.width(10.dp))
                Text(stat.message)
            }
        }
    }
}
