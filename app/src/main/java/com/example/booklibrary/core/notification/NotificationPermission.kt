package com.example.booklibrary.core.notification

import android.Manifest
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher

object NotificationPermission {

    private fun shouldRequestNotificationPermission(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    fun requestNotificationPermission(
        launcher: ManagedActivityResultLauncher<String, Boolean>
    ) {
        if (shouldRequestNotificationPermission()) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}