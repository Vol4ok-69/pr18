package com.example.pr18.service.pending

import com.example.pr18.model.PendingTask
import com.example.pr18.model.TaskState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object PendingTasksRepository {
    private val initial = listOf(
        PendingTask(PendingConstants.TASK1_CODE, "Task1", 7, TaskState.Waiting),
        PendingTask(PendingConstants.TASK2_CODE, "Task2", 4, TaskState.Waiting),
        PendingTask(PendingConstants.TASK3_CODE, "Task3", 6, TaskState.Waiting),
    )

    private val _tasks = MutableStateFlow(initial)
    val tasks: StateFlow<List<PendingTask>> = _tasks.asStateFlow()

    fun reset() {
        _tasks.value = initial
    }

    fun onStart(taskCode: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.code == taskCode) task.copy(state = TaskState.Started) else task
        }
    }

    fun onFinish(taskCode: Int, result: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.code == taskCode) task.copy(state = TaskState.Finished(result)) else task
        }
    }
}

