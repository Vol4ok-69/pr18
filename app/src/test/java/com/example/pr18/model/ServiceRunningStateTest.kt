package com.example.pr18.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ServiceRunningStateTest {
    @Test
    fun `simpleRunning toggles via updateSimpleRunning`() {
        ServiceRunningState.updateSimpleRunning(false)
        assertFalse(ServiceRunningState.simpleRunning)

        ServiceRunningState.updateSimpleRunning(true)
        assertTrue(ServiceRunningState.simpleRunning)
    }
}

