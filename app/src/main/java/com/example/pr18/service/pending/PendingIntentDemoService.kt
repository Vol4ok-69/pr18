package com.example.pr18.service.pending

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.utils.AppLogger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PendingIntentDemoService : Service() {
    private val tag = "PendingIntentService"

    private lateinit var executor: ExecutorService

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
        executor = Executors.newFixedThreadPool(2)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeSec = intent?.getIntExtra(PendingConstants.EXTRA_TIME_SEC, 0) ?: 0
        val taskCode = intent?.getIntExtra(PendingConstants.EXTRA_TASK_CODE, -1) ?: -1
        val pi = intent?.getParcelableExtra(PendingConstants.EXTRA_PENDING_INTENT, PendingIntent::class.java)

        AppLogger.d(tag, "onStartCommand startId=$startId task=$taskCode time=$timeSec")

        if (pi == null || taskCode == -1 || timeSec <= 0) {
            AppLogger.d(tag, "invalid start params -> stopSelfResult($startId)=${stopSelfResult(startId)}")
            return START_NOT_STICKY
        }

        val run = PendingRun(
            startId = startId,
            taskCode = taskCode,
            timeSec = timeSec,
            pi = pi,
        )
        executor.execute(run)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        executor.shutdownNow()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private inner class PendingRun(
        private val startId: Int,
        private val taskCode: Int,
        private val timeSec: Int,
        private val pi: PendingIntent,
    ) : Runnable {
        override fun run() {
            try {
                AppLogger.d(tag, "Task $taskCode start, time=$timeSec")
                pi.send(
                    applicationContext,
                    0,
                    Intent(PendingConstants.ACTION_PENDING_RESULT)
                        .putExtra(PendingConstants.EXTRA_TASK_CODE, taskCode)
                        .putExtra(PendingConstants.EXTRA_STATUS, PendingConstants.STATUS_START),
                )

                Thread.sleep(timeSec * 1000L)

                val result = timeSec * 100
                AppLogger.d(tag, "Task $taskCode finish, result=$result")
                pi.send(
                    applicationContext,
                    0,
                    Intent(PendingConstants.ACTION_PENDING_RESULT)
                        .putExtra(PendingConstants.EXTRA_TASK_CODE, taskCode)
                        .putExtra(PendingConstants.EXTRA_STATUS, PendingConstants.STATUS_FINISH)
                        .putExtra(PendingConstants.EXTRA_RESULT, result),
                )
            } catch (e: Exception) {
                AppLogger.d(tag, "Task $taskCode error: ${e.javaClass.simpleName}")
            } finally {
                AppLogger.d(tag, "Task $taskCode end, stopSelfResult($startId) = ${stopSelfResult(startId)}")
            }
        }
    }
}
