package com.eliteapps.tiym.model.singleNotificationScheduler

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.eliteapps.tiym.MainActivity
import com.eliteapps.tiym.R
import com.eliteapps.tiym.model.BackgroundCounter
import io.karn.notify.Notify
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class NotificationCreator(private val context: Context) {
	
	fun createNotification(period: Long, id: Int): Notification {
		
		val activity1String = "A 1"
		val activity2String = "A 2"
		
		val counterAction1Intent = Intent(context, BackgroundCounter::class.java)
			.putExtra(BackgroundCounter.ACTIVITY, activity1String)
			.putExtra(NotificationPublisher.ID, id)
		
		val counterAction1PendingIntent: PendingIntent = PendingIntent.getBroadcast(
			context,
			createPendingIntentID(),
			counterAction1Intent,
			PendingIntent.FLAG_UPDATE_CURRENT)
		
		val notificationAction1: NotificationCompat.Action = NotificationCompat.Action(
			R.drawable.ic_timeline, activity1String, counterAction1PendingIntent)
		
		
		val counterAction2Intent = Intent(context, BackgroundCounter::class.java)
			.putExtra(BackgroundCounter.ACTIVITY, activity2String)
			.putExtra(NotificationPublisher.ID, id)
		
		val counterAction2PendingIntent: PendingIntent = PendingIntent.getBroadcast(
			context,
			createPendingIntentID(),
			counterAction2Intent,
			PendingIntent.FLAG_UPDATE_CURRENT)
		
		val notificationAction2: NotificationCompat.Action = NotificationCompat.Action(
			R.drawable.ic_timeline, activity2String, counterAction2PendingIntent)
		
		
		val counterAction3Intent = Intent(context, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
		}
		
		val counterAction3PendingIntent: PendingIntent = PendingIntent.getActivity(
			context,
			createPendingIntentID(),
			counterAction3Intent,
			PendingIntent.FLAG_UPDATE_CURRENT)
		
		val notificationAction3: NotificationCompat.Action = NotificationCompat.Action(
			R.drawable.ic_timeline, "Other", counterAction3PendingIntent)
		
		val notification = Notify.with(context).content {
			title = "Last ${TimeUnit.MILLISECONDS.toMinutes(period)} Minutes"
			
		}.actions {
			add(notificationAction1)
			add(notificationAction2)
		 	add(notificationAction3)
		}
		
		return notification.asBuilder().build()
	}
	
	private fun createPendingIntentID(): Int {
		return Random.nextInt(100000000)
	}
	
}