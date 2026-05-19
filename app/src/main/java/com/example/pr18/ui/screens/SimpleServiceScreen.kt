package com.example.pr18.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pr18.model.ServiceRunningState
import com.example.pr18.service.simple.SimpleService
import com.example.pr18.ui.components.Pr18TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleServiceScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val running = ServiceRunningState.simpleRunning

    Scaffold(
        topBar = { Pr18TopAppBar(title = "Simple Service", onBack = onBack) },
        modifier = Modifier.fillMaxSize(),
    ) { inner ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { startSimple(context) }) { Text("Start service") }
                Button(onClick = { stopSimple(context) }) { Text("Stop service") }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (running) CircularProgressIndicator()
                Text(
                    text = if (running) "Service running" else "Service stopped",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

private fun startSimple(context: Context) {
    context.startService(Intent(context, SimpleService::class.java))
}

private fun stopSimple(context: Context) {
    context.stopService(Intent(context, SimpleService::class.java))
}
