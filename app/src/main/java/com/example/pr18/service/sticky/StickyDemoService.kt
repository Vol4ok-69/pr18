package com.example.pr18.service.sticky

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.utils.AppLogger

class StickyDemoService : Service() {
    private val tag = "StickyDemoService"
    private var lastPayload: String? = null
    private var lastMode: Int = START_NOT_STICKY

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_KILL) {
            AppLogger.d(tag, "kill imitation received: stopSelf() and restart logic")
            val mode = lastMode
            val restartIntent = when (mode) {
                START_REDELIVER_INTENT -> Intent(applicationContext, StickyDemoService::class.java)
                    .putExtra(EXTRA_MODE, mode)
                    .putExtra(EXTRA_PAYLOAD, lastPayload ?: "<restored>")

                START_STICKY -> Intent(applicationContext, StickyDemoService::class.java)
                    .putExtra(EXTRA_MODE, mode)
                    .putExtra(EXTRA_PAYLOAD, "<null-like intent simulation>")

                else -> null
            }

            stopSelf()
            if (restartIntent != null) {
                AppLogger.d(tag, "restart after kill imitation, mode=$mode")
                startService(restartIntent)
            } else {
                AppLogger.d(tag, "mode=START_NOT_STICKY -> no restart")
            }
            return START_NOT_STICKY
        }

        val mode = intent?.getIntExtra(EXTRA_MODE, START_NOT_STICKY) ?: START_NOT_STICKY
        val payload = intent?.getStringExtra(EXTRA_PAYLOAD) ?: "<no payload>"
        lastMode = mode
        lastPayload = payload

        AppLogger.d(tag, "onStartCommand startId=$startId flags=$flags mode=$mode payload=$payload")
        AppLogger.d(tag, "START_FLAG_REDELIVERY=${(flags and START_FLAG_REDELIVERY) != 0}")
        AppLogger.d(tag, "START_FLAG_RETRY=${(flags and START_FLAG_RETRY) != 0}")

        when (mode) {
            START_NOT_STICKY -> AppLogger.d(tag, "behavior: START_NOT_STICKY (no restart)")
            START_STICKY -> AppLogger.d(tag, "behavior: START_STICKY (restart, null intent possible)")
            START_REDELIVER_INTENT -> AppLogger.d(tag, "behavior: START_REDELIVER_INTENT (redeliver last intent)")
        }

        return mode
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val EXTRA_MODE = "extra_mode"
        const val EXTRA_PAYLOAD = "extra_payload"
        const val ACTION_KILL = "action_kill_imitation"
    }
}
