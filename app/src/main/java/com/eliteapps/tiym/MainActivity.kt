package com.eliteapps.tiym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eliteapps.tiym.model.NotificationScheduler
import com.eliteapps.tiym.ui.main.NotificationFrequencyFragment

class MainActivity : AppCompatActivity() {

    val notificationScheduler = NotificationScheduler(this)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, NotificationFrequencyFragment.newInstance())
                    .commitNow()
        }
    }
}