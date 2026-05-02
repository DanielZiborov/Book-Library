package com.example.booklibrary.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.booklibrary.MainActivity
import com.example.booklibrary.R
import com.example.booklibrary.domain.usecases.IsReadingBooksUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.example.booklibrary.core.usecases.invoke

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val isReadingBooksUseCase: IsReadingBooksUseCase
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val hasBooks = isReadingBooksUseCase()
        if (hasBooks) {
            showNotification()
        }
        return Result.success()
    }

    private fun showNotification() {
        val channelId = "channel_books_in_progress"

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Periodic Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Channel for periodic notifications"
        notificationManager.createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(
            applicationContext,
            channelId
        )
            .setSmallIcon(R.drawable.ic_launcher_round)
            .setContentTitle("Periodic Notification")
            .setContentText("Don't forget to finish reading the books")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}
