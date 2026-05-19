package com.example.pr18.service.stopself

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.utils.AppLogger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class StopServiceDemo : Service() {
    private val tag = "StopServiceDemo"

    private var executor: ExecutorService? = null
    private var currentPoolSize: Int? = null

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeSec = intent?.getIntExtra(EXTRA_TIME_SEC, 0) ?: 0
        val parallel = intent?.getBooleanExtra(EXTRA_PARALLEL, false) ?: false
        val poolSize = if (parallel) 3 else 1

        if (executor == null || currentPoolSize != poolSize) {
            AppLogger.d(tag, "create executor fixedThreadPool($poolSize)")
            executor?.shutdownNow()
            executor = Executors.newFixedThreadPool(poolSize)
            currentPoolSize = poolSize
        }

        AppLogger.d(tag, "onStartCommand startId=$startId flags=$flags time=$timeSec parallel=$parallel")
        val run = DemoRun(
            startId = startId,
            timeSec = timeSec,
        )
        AppLogger.d(tag, "MyRun#$startId create")

        executor?.execute(run)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        executor?.shutdownNow()
        executor = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private inner class DemoRun(
        private val startId: Int,
        private val timeSec: Int,
    ) : Runnable {
        override fun run() {
            AppLogger.d(tag, "MyRun#$startId start, time=$timeSec")
            try {
                Thread.sleep(timeSec * 1000L)
            } catch (_: InterruptedException) {
                AppLogger.d(tag, "MyRun#$startId interrupted")
            } finally {
                AppLogger.d(tag, "MyRun#$startId end, calling stopSelf($startId)")
                stopSelf(startId)
                AppLogger.d(tag, "MyRun#$startId stopSelfResult($startId) = ${stopSelfResult(startId)}")
            }
        }
    }

    companion object {
        const val EXTRA_TIME_SEC = "extra_time_sec"
        const val EXTRA_PARALLEL = "extra_parallel"
    }
}
