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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pr18.service.sticky.StickyDemoService
import com.example.pr18.ui.components.Pr18TopAppBar
import com.example.pr18.utils.AppLogger
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickyServiceScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    var mode by remember { mutableStateOf(StickyMode.NOT_STICKY) }
    val logs = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        AppLogger.events
            .filter { it.tag == "StickyDemoService" }
            .collect { event ->
                logs.add(event.message)
                if (logs.size > 200) logs.removeAt(0)
            }
    }

    Scaffold(
        topBar = { Pr18TopAppBar(title = "START_* modes", onBack = onBack) },
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
            StickyModeRadioGroup(
                mode = mode,
                onMode = { mode = it },
            )

            androidx.compose.foundation.layout.Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Button(onClick = {
                    logs.clear()
                    context.startService(
                        Intent(context, StickyDemoService::class.java)
                            .putExtra(StickyDemoService.EXTRA_MODE, mode.returnMode)
                            .putExtra(StickyDemoService.EXTRA_PAYLOAD, "payload @${System.currentTimeMillis()}"),
                    )
                }) { Text("Start service") }

                Button(onClick = {
                    AppLogger.d("StickyDemoService", "Kill imitation: stop + start")
                    context.startService(
                        Intent(context, StickyDemoService::class.java)
                            .setAction(StickyDemoService.ACTION_KILL)
                    )
                }) { Text("Kill process imitation") }
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

@Composable
private fun StickyModeRadioGroup(
    mode: StickyMode,
    onMode: (StickyMode) -> Unit,
) {
    androidx.compose.foundation.layout.Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        StickyMode.entries.forEach { item ->
            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                RadioButton(selected = item == mode, onClick = { onMode(item) })
                Text(item.title)
            }
        }
    }
}

private enum class StickyMode(val title: String, val returnMode: Int) {
    NOT_STICKY("START_NOT_STICKY", android.app.Service.START_NOT_STICKY),
    STICKY("START_STICKY", android.app.Service.START_STICKY),
    REDELIVER("START_REDELIVER_INTENT", android.app.Service.START_REDELIVER_INTENT),
}
