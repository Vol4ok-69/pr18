package com.example.pr18.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr18.ui.components.Pr18TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    contentPadding: PaddingValues,
    onOpenSimple: () -> Unit,
    onOpenStopSelf: () -> Unit,
    onOpenSticky: () -> Unit,
    onOpenPending: () -> Unit,
) {

    val items = listOf(
        "Simple Service" to onOpenSimple,
        "Service Stop / stopSelf" to onOpenStopSelf,
        "START_STICKY / START_NOT_STICKY / START_REDELIVER_INTENT" to onOpenSticky,
        "PendingIntent Service Result" to onOpenPending,
    )

    Scaffold(
        topBar = { Pr18TopAppBar(title = "PR18", onBack = null) },
        modifier = Modifier.fillMaxSize(),
    ) { inner ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            items(items) { (title, action) ->
                Button(
                    onClick = action,
                    modifier = Modifier.padding(vertical = 8.dp),
                ) {
                    Text(title)
                }
            }
        }
    }
}

