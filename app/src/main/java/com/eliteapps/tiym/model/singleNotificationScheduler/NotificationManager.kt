package com.eliteapps.tiym.model.singleNotificationScheduler

import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import java.lang.Exception

class NotificationManager() {
	
	private val trackedNotificationList = mutableListOf<TrackedNotification>()
	
	fun publishNotification(notification: Notification, context: Context, id: Int) {
		with(NotificationManagerCompat.from(context)) {
			
			try {
				
				notify(id, notification)
				trackedNotificationList += TrackedNotification(
					notification,
					id
				)
				
			} catch (e: Exception) {
				Log.e("NotificationManager", e.message, e)
			}
		}
	}
	private fun createId(): Int {
		return System.currentTimeMillis().toInt()
	}
}