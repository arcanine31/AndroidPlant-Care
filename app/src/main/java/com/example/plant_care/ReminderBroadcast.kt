package com.example.plant_care

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderBroadcast: BroadcastReceiver() {

    private val CHANNEL_ID = "channel_example"
    private val notificationID = 101

    override fun onReceive(context: Context, intent: Intent?) {
        val notifBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logoplant)
                .setContentTitle("Remember to care your plant")
                .setContentText("PlantNeed")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)){
            notify(notificationID, notifBuilder.build())
        }
    }

}