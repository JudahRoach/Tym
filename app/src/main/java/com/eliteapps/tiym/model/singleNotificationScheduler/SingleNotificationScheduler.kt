package com.eliteapps.tiym.model.singleNotificationScheduler

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class SingleNotificationScheduler(private val context: Context) {
	fun schedule(delay: Long): UUID {
		
		val data: Data = Data.Builder()
			.putLong(NotificationPublisher.PERIOD, delay)
			.build()
		
		val workRequest = OneTimeWorkRequestBuilder <NotificationPublisher>()
			.setInputData(data).setInitialDelay(delay, TimeUnit.MILLISECONDS).build()
		
		WorkManager
			.getInstance(context)
			.enqueue(workRequest)

		return workRequest.id
	}

	fun unschedule(id: UUID) {
		WorkManager
			.getInstance(context)
			.cancelWorkById(id)
	}

}