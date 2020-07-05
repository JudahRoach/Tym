package com.eliteapps.tiym.model.singleNotificationScheduler
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationPublisher(private val context: Context, private val workerParameters: WorkerParameters): Worker(context, workerParameters) {

	private val notificationManager = NotificationManager()
	private val notificationCreator = NotificationCreator(context)
	
	companion object {
		const val NOTIFICATION = "com.eliteapps.tiym.notification"
		const val PERIOD = "com.eliteapps.tiym.period"
		const val ID = "com.eliteapps.tiym.id"
	}
	

	
	override fun doWork(): Result {
		
		val period: Long = inputData.getLong(PERIOD, 0L)
		if (period == 0L) {
			Log.e("NotificationPublisher", "Delay not in input")
		}
		
		val id: Int = createId()
		
		val notification = notificationCreator.createNotification(period, id)
		
		try {
			notificationManager.publishNotification(notification, context, id)
		} catch (e: Exception) {
			Log.e("NotificationPublisher", e.message, e)
			return Result.failure()
		}
		
		return Result.success()
	}
	
	private fun createId(): Int {
		return System.currentTimeMillis().toInt()
	}
	
}