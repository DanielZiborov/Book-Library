package com.example.booklibrary.core.notification

import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberNotificationPermissionLauncher(
    onDenied: () -> Unit = {}
): ManagedActivityResultLauncher<String, Boolean> {
    val context = LocalContext.current

    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(
                context,
                "Permission denied",
                Toast.LENGTH_SHORT
            )
                .show()
            onDenied()
        }
    }
}