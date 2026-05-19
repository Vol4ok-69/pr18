package com.example.pr18.ui.screens

import android.app.PendingIntent
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pr18.model.PendingTask
import com.example.pr18.model.TaskState
import com.example.pr18.service.pending.PendingConstants
import com.example.pr18.service.pending.PendingIntentDemoService
import com.example.pr18.service.pending.PendingResultReceiver
import com.example.pr18.service.pending.PendingTasksRepository
import com.example.pr18.ui.components.Pr18TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingIntentScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val tasks by PendingTasksRepository.tasks.collectAsState()

    Scaffold(
        topBar = { Pr18TopAppBar(title = "PendingIntent result", onBack = onBack) },
        modifier = Modifier.fillMaxSize(),
    ) { inner ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(onClick = {
                PendingTasksRepository.reset()
                val pi = PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(context, PendingResultReceiver::class.java)
                        .setAction(PendingConstants.ACTION_PENDING_RESULT),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                )
                startPendingTasks(context, pi)
            }) {
                Text("Start")
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(tasks) { task ->
                    PendingTaskCard(task)
                }
            }
        }
    }
}

@Composable
private fun PendingTaskCard(task: PendingTask) {
    Card {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(task.title, style = MaterialTheme.typography.titleMedium)
            Text("Duration: ${task.durationSec}s")
            when (val state = task.state) {
                TaskState.Waiting -> Text("Status: Waiting")
                TaskState.Started -> Text("Status: Started")
                is TaskState.Finished -> {
                    Text("Status: Finished")
                    Text("Result: ${state.result}")
                }
            }
        }
    }
}

private fun startPendingTasks(context: android.content.Context, pi: PendingIntent) {
    fun start(taskCode: Int, timeSec: Int) {
        context.startService(
            Intent(context, PendingIntentDemoService::class.java)
                .putExtra(PendingConstants.EXTRA_TASK_CODE, taskCode)
                .putExtra(PendingConstants.EXTRA_TIME_SEC, timeSec)
                .putExtra(PendingConstants.EXTRA_PENDING_INTENT, pi),
        )
    }
    start(PendingConstants.TASK1_CODE, 7)
    start(PendingConstants.TASK2_CODE, 4)
    start(PendingConstants.TASK3_CODE, 6)
}
