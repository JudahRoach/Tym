package com.eliteapps.tiym.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.eliteapps.tiym.model.singleNotificationScheduler.NotificationPublisher

class BackgroundCounter: BroadcastReceiver() {
	

	override fun onReceive(context: Context?, intent: Intent?) {
		val countAction = intent?.getStringExtra(ACTIVITY)
			?: run { Log.e("BackgroundCounter", "Activity Missing")
			return }
		
		Log.i("BackgroundCounter", countAction)
		
		val notificationId = intent.getIntExtra(NotificationPublisher.ID, 0)
		
		if (notificationId == 0) {
				Log.e("BackgroundCounter", "Activity Missing")
				return
		} else {
			if (context != null) {
				with(NotificationManagerCompat.from(context)) {
					cancel(notificationId)
				}
			} else {
				Log.e("BackgroundCounter", "Notification cannot be dismissed due to null context")
			}
		}
	}
	
	companion object {
		const val ACTIVITY = "com.example.notificationtest.backgroundcounter.activity"
	}
}