package com.example.booklibrary.presentation.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.booklibrary.common.components.CustomTopAppBar
import com.example.booklibrary.core.notification.NotificationPermission
import com.example.booklibrary.core.notification.rememberNotificationPermissionLauncher
import com.example.booklibrary.presentation.viewmodels.BookNotificationViewModel
import com.example.booklibrary.presentation.views.TimePickerView

@Composable
fun SettingsScreen(
    bookNotificationViewModel: BookNotificationViewModel
) {
    val permissionLauncher = rememberNotificationPermissionLauncher()

    Scaffold(
        topBar = {
            CustomTopAppBar("Settings")
        },
    ) { paddingValues ->
        TimePickerView(
            paddingValues = paddingValues,
            setTime = { hour, minutes ->
                NotificationPermission.requestNotificationPermission(permissionLauncher)
                bookNotificationViewModel.scheduleNotification(hour, minutes)
            }
        )
    }
}
