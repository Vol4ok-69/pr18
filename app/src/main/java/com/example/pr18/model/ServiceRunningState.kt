package com.example.pr18.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ServiceRunningState {
    var simpleRunning by mutableStateOf(false)
        private set

    fun updateSimpleRunning(value: Boolean) {
        simpleRunning = value
    }
}
