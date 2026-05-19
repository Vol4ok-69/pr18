package com.example.pr18.model

data class PendingTask(
    val code: Int,
    val title: String,
    val durationSec: Int,
    val state: TaskState,
)

