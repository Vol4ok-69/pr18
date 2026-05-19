package com.example.pr18.utils

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object AppLogger {
    private const val DEFAULT_TAG = "pr18"

    private val _events = MutableSharedFlow<LogEvent>(
        replay = 0,
        extraBufferCapacity = 200,
    )
    val events = _events.asSharedFlow()

    fun d(tag: String = DEFAULT_TAG, message: String) {
        Log.d(tag, message)
        _events.tryEmit(LogEvent(tag = tag, message = message, timestampMs = System.currentTimeMillis()))
    }
}

data class LogEvent(
    val tag: String,
    val message: String,
    val timestampMs: Long,
)

