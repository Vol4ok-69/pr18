package com.example.pr18.service.simple

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.model.ServiceRunningState
import com.example.pr18.utils.AppLogger
import kotlin.concurrent.thread

class SimpleService : Service() {
    private val tag = "SimpleService"

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
        ServiceRunningState.updateSimpleRunning(true)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        AppLogger.d(tag, "onStartCommand startId=$startId")

        thread(name = "SimpleServiceThread") {
            for (i in 1..5) {
                AppLogger.d(tag, "i = $i")
                Thread.sleep(1000)
            }
            AppLogger.d(tag, "done -> stopSelf()")
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        ServiceRunningState.updateSimpleRunning(false)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
