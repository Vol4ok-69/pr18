package com.example.pr18.service.pending

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.pr18.utils.AppLogger

class PendingResultReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != PendingConstants.ACTION_PENDING_RESULT) return

        val taskCode = intent.getIntExtra(PendingConstants.EXTRA_TASK_CODE, -1)
        val status = intent.getIntExtra(PendingConstants.EXTRA_STATUS, -1)
        val result = intent.getIntExtra(PendingConstants.EXTRA_RESULT, 0)

        AppLogger.d("PendingReceiver", "onReceive: task=$taskCode status=$status result=$result")

        when (status) {
            PendingConstants.STATUS_START -> PendingTasksRepository.onStart(taskCode)
            PendingConstants.STATUS_FINISH -> PendingTasksRepository.onFinish(taskCode, result)
        }
    }
}

