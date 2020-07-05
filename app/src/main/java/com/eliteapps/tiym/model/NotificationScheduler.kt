package com.eliteapps.tiym.model

import android.content.Context
import com.eliteapps.tiym.model.singleNotificationScheduler.SingleNotificationScheduler
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationScheduler(context: Context) {
	
	private val notificationSchedule = arrayListOf<ScheduledNotification>()
	private val notificationScheduler = SingleNotificationScheduler(context)
	
	fun scheduleNotification(period: TimeUnit, date: Date, primaryAction: String, secondaryAction: String) {
		
		// if there is already a notification with the same time, unschedule it.
		unscheduleNotification(date)
		
		val id = notificationScheduler.schedule(date.toDelay())
		
		val scheduledNotification = ScheduledNotification(period, date, primaryAction, secondaryAction, id)
		
		notificationSchedule += scheduledNotification
		notificationSchedule.sortBy { it.date }
	}
	
	fun unscheduleNotification(date: Date) {
		val unscheduledNotifications = notificationSchedule.filter { it.date == date }
		
		// there should not be more than one notification scheduled for the same time, but if there is this function will remove all of them.
		
		unscheduleNotificationList(unscheduledNotifications)
		
	}
	
	fun removeOldNotifications() {
		val unscheduledNotifications = notificationSchedule.filter { it.date.time <= System.currentTimeMillis() }
		
		unscheduleNotificationList(unscheduledNotifications)
	}
	
	private fun unscheduleNotificationList(notificationList: List<ScheduledNotification>) {
		for (notification in notificationList) {
			
			notificationScheduler.unschedule(notification.id)
			
			notificationSchedule.remove(notification)
			
		}
	}

	companion object {
		fun Date.toDelay(): Long {
			return this.time - System.currentTimeMillis()
		}
		
		fun Long.toDelayedDate(): Date {
			return Date(System.currentTimeMillis() + this)
		}
	}
	
}