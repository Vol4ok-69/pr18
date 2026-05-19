package com.example.pr18.service.pending

import com.example.pr18.model.TaskState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PendingTasksRepositoryTest {
    @Test
    fun `reset restores three waiting tasks`() = runTest {
        PendingTasksRepository.onStart(PendingConstants.TASK1_CODE)
        PendingTasksRepository.onFinish(PendingConstants.TASK2_CODE, 123)

        PendingTasksRepository.reset()

        val tasks = PendingTasksRepository.tasks.value
        assertEquals(3, tasks.size)
        assertTrue(tasks.all { it.state is TaskState.Waiting })
        assertEquals(listOf("Task1", "Task2", "Task3"), tasks.map { it.title })
        assertEquals(listOf(7, 4, 6), tasks.map { it.durationSec })
    }

    @Test
    fun `onStart changes task state to Started`() = runTest {
        PendingTasksRepository.reset()

        PendingTasksRepository.onStart(PendingConstants.TASK2_CODE)

        val task2 = PendingTasksRepository.tasks.value.first { it.code == PendingConstants.TASK2_CODE }
        assertTrue(task2.state is TaskState.Started)
    }

    @Test
    fun `onFinish changes task state to Finished with result`() = runTest {
        PendingTasksRepository.reset()

        PendingTasksRepository.onFinish(PendingConstants.TASK3_CODE, 600)

        val task3 = PendingTasksRepository.tasks.value.first { it.code == PendingConstants.TASK3_CODE }
        val state = task3.state
        assertTrue(state is TaskState.Finished)
        assertEquals(600, (state as TaskState.Finished).result)
    }
}

