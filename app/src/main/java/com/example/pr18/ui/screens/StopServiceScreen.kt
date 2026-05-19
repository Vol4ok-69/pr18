package com.example.pr18.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pr18.service.stopself.StopServiceDemo
import com.example.pr18.ui.components.Pr18TopAppBar
import com.example.pr18.utils.AppLogger
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopServiceScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    var parallel by remember { mutableStateOf(false) }
    val logs = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        AppLogger.events
            .filter { it.tag == "StopServiceDemo" }
            .collect { event ->
                logs.add("${event.message}")
                if (logs.size > 200) logs.removeAt(0)
            }
    }

    Scaffold(
        topBar = { Pr18TopAppBar(title = "stopSelf(startId)", onBack = onBack) },
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
            androidx.compose.foundation.layout.Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Parallel execution")
                Switch(
                    checked = parallel,
                    onCheckedChange = { parallel = it },
                )
            }

            Button(
                onClick = {
                    logs.clear()
                    startStopSelfDemo(context, parallel)
                },
            ) {
                Text("Start")
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                items(logs.toList()) { line ->
                    Text(line, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

private fun startStopSelfDemo(context: android.content.Context, parallel: Boolean) {
    fun start(timeSec: Int) {
        context.startService(
            Intent(context, StopServiceDemo::class.java)
                .putExtra(StopServiceDemo.EXTRA_TIME_SEC, timeSec)
                .putExtra(StopServiceDemo.EXTRA_PARALLEL, parallel),
        )
    }

    start(7)
    start(2)
    start(4)
}

