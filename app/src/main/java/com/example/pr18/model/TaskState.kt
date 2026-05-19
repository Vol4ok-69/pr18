package com.example.pr18.model

sealed class TaskState {
    data object Waiting : TaskState()
    data object Started : TaskState()
    data class Finished(val result: Int) : TaskState()
}

