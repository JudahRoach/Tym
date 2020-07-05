package com.eliteapps.tiym

import com.eliteapps.tiym.model.NotificationScheduler.Companion.toDelayedDate
import com.eliteapps.tiym.model.NotificationScheduler.Companion.toDelay
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.random.Random

class NotificationSchedulerUnitTest {
	
	@Test
	fun dateToDelayIsCorrect() {
		
		val randomOffset = Random.nextInt(2000000).toLong()
		
		val fakeFutureDate = Date(System.currentTimeMillis() + randomOffset)
		
		assertEquals(randomOffset, fakeFutureDate.toDelay())
	}
	
	@Test
	fun delaytoDateIsCorrect() {
		
		val randomDelay = Random.nextInt(2000000)
		
		val futureDate = Date(System.currentTimeMillis() + randomDelay)
		
		assertEquals(futureDate, randomDelay.toLong().toDelayedDate())
	}
	
}