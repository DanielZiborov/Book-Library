package com.example.booklibrary.utils

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun showConfirm(
    message: String,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    withDismissAction: Boolean = true,
    actionLabel: String = "YES",
    consentAction: () -> Unit
) {
    coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            withDismissAction = withDismissAction
        )
        if (result == SnackbarResult.ActionPerformed) {
            consentAction()
        }
    }
}