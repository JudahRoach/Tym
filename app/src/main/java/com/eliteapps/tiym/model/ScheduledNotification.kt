package com.eliteapps.tiym.model

import java.util.*
import java.util.concurrent.TimeUnit

data class ScheduledNotification(val period: TimeUnit, val date: Date, val primaryAction: String, val secondaryAction: String, val id: UUID) {}