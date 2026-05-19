# Project Structure

```text
pr18
├── .kotlin
│   └── sessions
├── app
│   ├── src
│   │   ├── androidTest
│   │   │   └── java
│   │   │       └── com
│   │   │           └── example
│   │   │               └── pr18
│   │   │                   └── ExampleInstrumentedTest.kt
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── pr18
│   │   │   │               ├── model
│   │   │   │               │   ├── PendingTask.kt
│   │   │   │               │   ├── ServiceRunningState.kt
│   │   │   │               │   └── TaskState.kt
│   │   │   │               ├── service
│   │   │   │               │   ├── pending
│   │   │   │               │   │   ├── PendingConstants.kt
│   │   │   │               │   │   ├── PendingIntentDemoService.kt
│   │   │   │               │   │   ├── PendingResultReceiver.kt
│   │   │   │               │   │   └── PendingTasksRepository.kt
│   │   │   │               │   ├── simple
│   │   │   │               │   │   └── SimpleService.kt
│   │   │   │               │   ├── sticky
│   │   │   │               │   │   └── StickyDemoService.kt
│   │   │   │               │   └── stopself
│   │   │   │               │       └── StopServiceDemo.kt
│   │   │   │               ├── ui
│   │   │   │               │   ├── components
│   │   │   │               │   │   └── Pr18TopAppBar.kt
│   │   │   │               │   ├── nav
│   │   │   │               │   │   ├── Pr18Destinations.kt
│   │   │   │               │   │   └── Pr18Graph.kt
│   │   │   │               │   ├── screens
│   │   │   │               │   │   ├── MainMenuScreen.kt
│   │   │   │               │   │   ├── PendingIntentScreen.kt
│   │   │   │               │   │   ├── SimpleServiceScreen.kt
│   │   │   │               │   │   ├── StickyServiceScreen.kt
│   │   │   │               │   │   └── StopServiceScreen.kt
│   │   │   │               │   ├── theme
│   │   │   │               │   │   ├── Color.kt
│   │   │   │               │   │   ├── Theme.kt
│   │   │   │               │   │   └── Type.kt
│   │   │   │               │   └── Pr18App.kt
│   │   │   │               ├── utils
│   │   │   │               │   └── AppLogger.kt
│   │   │   │               └── MainActivity.kt
│   │   │   ├── res
│   │   │   │   ├── drawable
│   │   │   │   │   ├── ic_launcher_background.xml
│   │   │   │   │   └── ic_launcher_foreground.xml
│   │   │   │   ├── mipmap-anydpi
│   │   │   │   │   ├── ic_launcher_round.xml
│   │   │   │   │   └── ic_launcher.xml
│   │   │   │   ├── mipmap-hdpi
│   │   │   │   │   ├── ic_launcher_round.webp
│   │   │   │   │   └── ic_launcher.webp
│   │   │   │   ├── mipmap-mdpi
│   │   │   │   │   ├── ic_launcher_round.webp
│   │   │   │   │   └── ic_launcher.webp
│   │   │   │   ├── mipmap-xhdpi
│   │   │   │   │   ├── ic_launcher_round.webp
│   │   │   │   │   └── ic_launcher.webp
│   │   │   │   ├── mipmap-xxhdpi
│   │   │   │   │   ├── ic_launcher_round.webp
│   │   │   │   │   └── ic_launcher.webp
│   │   │   │   ├── mipmap-xxxhdpi
│   │   │   │   │   ├── ic_launcher_round.webp
│   │   │   │   │   └── ic_launcher.webp
│   │   │   │   ├── values
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── xml
│   │   │   │       ├── backup_rules.xml
│   │   │   │       └── data_extraction_rules.xml
│   │   │   └── AndroidManifest.xml
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── example
│   │                   └── pr18
│   │                       ├── model
│   │                       │   └── ServiceRunningStateTest.kt
│   │                       ├── service
│   │                       │   └── pending
│   │                       │       └── PendingTasksRepositoryTest.kt
│   │                       └── ExampleUnitTest.kt
│   ├── .gitignore
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle
│   ├── wrapper
│   │   ├── gradle-wrapper.jar
│   │   └── gradle-wrapper.properties
│   └── libs.versions.toml
├── .gitignore
├── ПЗ.18 Приложение с использованием сервисов.docx
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts

```


# File Contents

## app\src\androidTest\java\com\example\pr18\ExampleInstrumentedTest.kt

```kotlin
package com.example.pr18

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.pr18", appContext.packageName)
    }
}
```

## app\src\main\java\com\example\pr18\model\PendingTask.kt

```kotlin
package com.example.pr18.model

data class PendingTask(
    val code: Int,
    val title: String,
    val durationSec: Int,
    val state: TaskState,
)


```

## app\src\main\java\com\example\pr18\model\ServiceRunningState.kt

```kotlin
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

```

## app\src\main\java\com\example\pr18\model\TaskState.kt

```kotlin
package com.example.pr18.model

sealed class TaskState {
    data object Waiting : TaskState()
    data object Started : TaskState()
    data class Finished(val result: Int) : TaskState()
}


```

## app\src\main\java\com\example\pr18\service\pending\PendingConstants.kt

```kotlin
package com.example.pr18.service.pending

object PendingConstants {
    const val ACTION_PENDING_RESULT = "com.example.pr18.ACTION_PENDING_RESULT"

    const val EXTRA_TASK_CODE = "extra_task_code"
    const val EXTRA_STATUS = "extra_status"
    const val EXTRA_RESULT = "extra_result"

    const val EXTRA_PENDING_INTENT = "extra_pending_intent"
    const val EXTRA_TIME_SEC = "extra_time_sec"

    const val STATUS_START = 100
    const val STATUS_FINISH = 200

    const val TASK1_CODE = 1
    const val TASK2_CODE = 2
    const val TASK3_CODE = 3
}


```

## app\src\main\java\com\example\pr18\service\pending\PendingIntentDemoService.kt

```kotlin
package com.example.pr18.service.pending

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.utils.AppLogger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PendingIntentDemoService : Service() {
    private val tag = "PendingIntentService"

    private lateinit var executor: ExecutorService

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
        executor = Executors.newFixedThreadPool(2)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeSec = intent?.getIntExtra(PendingConstants.EXTRA_TIME_SEC, 0) ?: 0
        val taskCode = intent?.getIntExtra(PendingConstants.EXTRA_TASK_CODE, -1) ?: -1
        val pi = intent?.getParcelableExtra(PendingConstants.EXTRA_PENDING_INTENT, PendingIntent::class.java)

        AppLogger.d(tag, "onStartCommand startId=$startId task=$taskCode time=$timeSec")

        if (pi == null || taskCode == -1 || timeSec <= 0) {
            AppLogger.d(tag, "invalid start params -> stopSelfResult($startId)=${stopSelfResult(startId)}")
            return START_NOT_STICKY
        }

        val run = PendingRun(
            startId = startId,
            taskCode = taskCode,
            timeSec = timeSec,
            pi = pi,
        )
        executor.execute(run)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        executor.shutdownNow()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private inner class PendingRun(
        private val startId: Int,
        private val taskCode: Int,
        private val timeSec: Int,
        private val pi: PendingIntent,
    ) : Runnable {
        override fun run() {
            try {
                AppLogger.d(tag, "Task $taskCode start, time=$timeSec")
                pi.send(
                    applicationContext,
                    0,
                    Intent(PendingConstants.ACTION_PENDING_RESULT)
                        .putExtra(PendingConstants.EXTRA_TASK_CODE, taskCode)
                        .putExtra(PendingConstants.EXTRA_STATUS, PendingConstants.STATUS_START),
                )

                Thread.sleep(timeSec * 1000L)

                val result = timeSec * 100
                AppLogger.d(tag, "Task $taskCode finish, result=$result")
                pi.send(
                    applicationContext,
                    0,
                    Intent(PendingConstants.ACTION_PENDING_RESULT)
                        .putExtra(PendingConstants.EXTRA_TASK_CODE, taskCode)
                        .putExtra(PendingConstants.EXTRA_STATUS, PendingConstants.STATUS_FINISH)
                        .putExtra(PendingConstants.EXTRA_RESULT, result),
                )
            } catch (e: Exception) {
                AppLogger.d(tag, "Task $taskCode error: ${e.javaClass.simpleName}")
            } finally {
                AppLogger.d(tag, "Task $taskCode end, stopSelfResult($startId) = ${stopSelfResult(startId)}")
            }
        }
    }
}

```

## app\src\main\java\com\example\pr18\service\pending\PendingResultReceiver.kt

```kotlin
package com.example.pr18.service.pending

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.pr18.utils.AppLogger

class PendingResultReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != PendingConstants.ACTION_PENDING_RESULT) return

        val taskCode = intent.getIntExtra(PendingConstants.EXTRA_TASK_CODE, -1)
        val status = intent.getIntExtra(PendingConstants.EXTRA_STATUS, -1)
        val result = intent.getIntExtra(PendingConstants.EXTRA_RESULT, 0)

        AppLogger.d("PendingReceiver", "onReceive: task=$taskCode status=$status result=$result")

        when (status) {
            PendingConstants.STATUS_START -> PendingTasksRepository.onStart(taskCode)
            PendingConstants.STATUS_FINISH -> PendingTasksRepository.onFinish(taskCode, result)
        }
    }
}


```

## app\src\main\java\com\example\pr18\service\pending\PendingTasksRepository.kt

```kotlin
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


```

## app\src\main\java\com\example\pr18\service\simple\SimpleService.kt

```kotlin
package com.example.pr18.service.simple

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.model.ServiceRunningState
import com.example.pr18.utils.AppLogger
import kotlin.concurrent.thread

class SimpleService : Service() {
    private val tag = "SimpleService"

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
        ServiceRunningState.updateSimpleRunning(true)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        AppLogger.d(tag, "onStartCommand startId=$startId")

        thread(name = "SimpleServiceThread") {
            for (i in 1..5) {
                AppLogger.d(tag, "i = $i")
                Thread.sleep(1000)
            }
            AppLogger.d(tag, "done -> stopSelf()")
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        ServiceRunningState.updateSimpleRunning(false)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

```

## app\src\main\java\com\example\pr18\service\sticky\StickyDemoService.kt

```kotlin
package com.example.pr18.service.sticky

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.utils.AppLogger

class StickyDemoService : Service() {
    private val tag = "StickyDemoService"
    private var lastPayload: String? = null
    private var lastMode: Int = START_NOT_STICKY

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_KILL) {
            AppLogger.d(tag, "kill imitation received: stopSelf() and restart logic")
            val mode = lastMode
            val restartIntent = when (mode) {
                START_REDELIVER_INTENT -> Intent(applicationContext, StickyDemoService::class.java)
                    .putExtra(EXTRA_MODE, mode)
                    .putExtra(EXTRA_PAYLOAD, lastPayload ?: "<restored>")

                START_STICKY -> Intent(applicationContext, StickyDemoService::class.java)
                    .putExtra(EXTRA_MODE, mode)
                    .putExtra(EXTRA_PAYLOAD, "<null-like intent simulation>")

                else -> null
            }

            stopSelf()
            if (restartIntent != null) {
                AppLogger.d(tag, "restart after kill imitation, mode=$mode")
                startService(restartIntent)
            } else {
                AppLogger.d(tag, "mode=START_NOT_STICKY -> no restart")
            }
            return START_NOT_STICKY
        }

        val mode = intent?.getIntExtra(EXTRA_MODE, START_NOT_STICKY) ?: START_NOT_STICKY
        val payload = intent?.getStringExtra(EXTRA_PAYLOAD) ?: "<no payload>"
        lastMode = mode
        lastPayload = payload

        AppLogger.d(tag, "onStartCommand startId=$startId flags=$flags mode=$mode payload=$payload")
        AppLogger.d(tag, "START_FLAG_REDELIVERY=${(flags and START_FLAG_REDELIVERY) != 0}")
        AppLogger.d(tag, "START_FLAG_RETRY=${(flags and START_FLAG_RETRY) != 0}")

        when (mode) {
            START_NOT_STICKY -> AppLogger.d(tag, "behavior: START_NOT_STICKY (no restart)")
            START_STICKY -> AppLogger.d(tag, "behavior: START_STICKY (restart, null intent possible)")
            START_REDELIVER_INTENT -> AppLogger.d(tag, "behavior: START_REDELIVER_INTENT (redeliver last intent)")
        }

        return mode
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val EXTRA_MODE = "extra_mode"
        const val EXTRA_PAYLOAD = "extra_payload"
        const val ACTION_KILL = "action_kill_imitation"
    }
}

```

## app\src\main\java\com\example\pr18\service\stopself\StopServiceDemo.kt

```kotlin
package com.example.pr18.service.stopself

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pr18.utils.AppLogger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class StopServiceDemo : Service() {
    private val tag = "StopServiceDemo"

    private var executor: ExecutorService? = null
    private var currentPoolSize: Int? = null

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(tag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeSec = intent?.getIntExtra(EXTRA_TIME_SEC, 0) ?: 0
        val parallel = intent?.getBooleanExtra(EXTRA_PARALLEL, false) ?: false
        val poolSize = if (parallel) 3 else 1

        if (executor == null || currentPoolSize != poolSize) {
            AppLogger.d(tag, "create executor fixedThreadPool($poolSize)")
            executor?.shutdownNow()
            executor = Executors.newFixedThreadPool(poolSize)
            currentPoolSize = poolSize
        }

        AppLogger.d(tag, "onStartCommand startId=$startId flags=$flags time=$timeSec parallel=$parallel")
        val run = DemoRun(
            startId = startId,
            timeSec = timeSec,
        )
        AppLogger.d(tag, "MyRun#$startId create")

        executor?.execute(run)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        AppLogger.d(tag, "onDestroy")
        executor?.shutdownNow()
        executor = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private inner class DemoRun(
        private val startId: Int,
        private val timeSec: Int,
    ) : Runnable {
        override fun run() {
            AppLogger.d(tag, "MyRun#$startId start, time=$timeSec")
            try {
                Thread.sleep(timeSec * 1000L)
            } catch (_: InterruptedException) {
                AppLogger.d(tag, "MyRun#$startId interrupted")
            } finally {
                AppLogger.d(tag, "MyRun#$startId end, calling stopSelf($startId)")
                stopSelf(startId)
                AppLogger.d(tag, "MyRun#$startId stopSelfResult($startId) = ${stopSelfResult(startId)}")
            }
        }
    }

    companion object {
        const val EXTRA_TIME_SEC = "extra_time_sec"
        const val EXTRA_PARALLEL = "extra_parallel"
    }
}

```

## app\src\main\java\com\example\pr18\ui\components\Pr18TopAppBar.kt

```kotlin
package com.example.pr18.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pr18TopAppBar(
    title: String,
    onBack: (() -> Unit)?,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_media_previous),
                        contentDescription = "Back",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    )
}

```

## app\src\main\java\com\example\pr18\ui\nav\Pr18Destinations.kt

```kotlin
package com.example.pr18.ui.nav

sealed class Pr18Destinations(val route: String) {
    data object MainMenu : Pr18Destinations("main_menu")
    data object SimpleService : Pr18Destinations("simple_service")
    data object StopSelf : Pr18Destinations("stop_self")
    data object StickyModes : Pr18Destinations("sticky_modes")
    data object PendingIntent : Pr18Destinations("pending_intent")
}


```

## app\src\main\java\com\example\pr18\ui\nav\Pr18Graph.kt

```kotlin
package com.example.pr18.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.pr18.ui.screens.MainMenuScreen
import com.example.pr18.ui.screens.PendingIntentScreen
import com.example.pr18.ui.screens.SimpleServiceScreen
import com.example.pr18.ui.screens.StickyServiceScreen
import com.example.pr18.ui.screens.StopServiceScreen

fun androidx.navigation.NavGraphBuilder.pr18Graph(
    navController: NavHostController,
    contentPadding: PaddingValues,
) {
    composable(Pr18Destinations.MainMenu.route) {
        MainMenuScreen(
            contentPadding = contentPadding,
            onOpenSimple = { navController.navigate(Pr18Destinations.SimpleService.route) },
            onOpenStopSelf = { navController.navigate(Pr18Destinations.StopSelf.route) },
            onOpenSticky = { navController.navigate(Pr18Destinations.StickyModes.route) },
            onOpenPending = { navController.navigate(Pr18Destinations.PendingIntent.route) },
        )
    }

    composable(Pr18Destinations.SimpleService.route) {
        SimpleServiceScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }

    composable(Pr18Destinations.StopSelf.route) {
        StopServiceScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }

    composable(Pr18Destinations.StickyModes.route) {
        StickyServiceScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }

    composable(Pr18Destinations.PendingIntent.route) {
        PendingIntentScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }
}


```

## app\src\main\java\com\example\pr18\ui\screens\MainMenuScreen.kt

```kotlin
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


```

## app\src\main\java\com\example\pr18\ui\screens\PendingIntentScreen.kt

```kotlin
package com.example.pr18.ui.screens

import android.app.PendingIntent
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pr18.model.PendingTask
import com.example.pr18.model.TaskState
import com.example.pr18.service.pending.PendingConstants
import com.example.pr18.service.pending.PendingIntentDemoService
import com.example.pr18.service.pending.PendingResultReceiver
import com.example.pr18.service.pending.PendingTasksRepository
import com.example.pr18.ui.components.Pr18TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingIntentScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val tasks by PendingTasksRepository.tasks.collectAsState()

    Scaffold(
        topBar = { Pr18TopAppBar(title = "PendingIntent result", onBack = onBack) },
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
            Button(onClick = {
                PendingTasksRepository.reset()
                val pi = PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(context, PendingResultReceiver::class.java)
                        .setAction(PendingConstants.ACTION_PENDING_RESULT),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                )
                startPendingTasks(context, pi)
            }) {
                Text("Start")
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(tasks) { task ->
                    PendingTaskCard(task)
                }
            }
        }
    }
}

@Composable
private fun PendingTaskCard(task: PendingTask) {
    Card {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(task.title, style = MaterialTheme.typography.titleMedium)
            Text("Duration: ${task.durationSec}s")
            when (val state = task.state) {
                TaskState.Waiting -> Text("Status: Waiting")
                TaskState.Started -> Text("Status: Started")
                is TaskState.Finished -> {
                    Text("Status: Finished")
                    Text("Result: ${state.result}")
                }
            }
        }
    }
}

private fun startPendingTasks(context: android.content.Context, pi: PendingIntent) {
    fun start(taskCode: Int, timeSec: Int) {
        context.startService(
            Intent(context, PendingIntentDemoService::class.java)
                .putExtra(PendingConstants.EXTRA_TASK_CODE, taskCode)
                .putExtra(PendingConstants.EXTRA_TIME_SEC, timeSec)
                .putExtra(PendingConstants.EXTRA_PENDING_INTENT, pi),
        )
    }
    start(PendingConstants.TASK1_CODE, 7)
    start(PendingConstants.TASK2_CODE, 4)
    start(PendingConstants.TASK3_CODE, 6)
}

```

## app\src\main\java\com\example\pr18\ui\screens\SimpleServiceScreen.kt

```kotlin
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

```

## app\src\main\java\com\example\pr18\ui\screens\StickyServiceScreen.kt

```kotlin
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

```

## app\src\main\java\com\example\pr18\ui\screens\StopServiceScreen.kt

```kotlin
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pr18.service.stopself.StopServiceDemo
import com.example.pr18.ui.components.Pr18TopAppBar
import com.example.pr18.utils.AppLogger
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopServiceScreen(
    contentPadding: PaddingValues,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    var parallel by remember { mutableStateOf(false) }
    val logs = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        AppLogger.events
            .filter { it.tag == "StopServiceDemo" }
            .collect { event ->
                logs.add("${event.message}")
                if (logs.size > 200) logs.removeAt(0)
            }
    }

    Scaffold(
        topBar = { Pr18TopAppBar(title = "stopSelf(startId)", onBack = onBack) },
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
            androidx.compose.foundation.layout.Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Parallel execution")
                Switch(
                    checked = parallel,
                    onCheckedChange = { parallel = it },
                )
            }

            Button(
                onClick = {
                    logs.clear()
                    startStopSelfDemo(context, parallel)
                },
            ) {
                Text("Start")
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

private fun startStopSelfDemo(context: android.content.Context, parallel: Boolean) {
    fun start(timeSec: Int) {
        context.startService(
            Intent(context, StopServiceDemo::class.java)
                .putExtra(StopServiceDemo.EXTRA_TIME_SEC, timeSec)
                .putExtra(StopServiceDemo.EXTRA_PARALLEL, parallel),
        )
    }

    start(7)
    start(2)
    start(4)
}


```

## app\src\main\java\com\example\pr18\ui\theme\Color.kt

```kotlin
package com.example.pr18.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
```

## app\src\main\java\com\example\pr18\ui\theme\Theme.kt

```kotlin
package com.example.pr18.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Pr18Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

## app\src\main\java\com\example\pr18\ui\theme\Type.kt

```kotlin
package com.example.pr18.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
```

## app\src\main\java\com\example\pr18\ui\Pr18App.kt

```kotlin
package com.example.pr18.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pr18.ui.nav.Pr18Destinations
import com.example.pr18.ui.nav.pr18Graph

@Composable
fun Pr18App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Pr18Destinations.MainMenu.route,
        modifier = modifier,
    ) {
        pr18Graph(
            navController = navController,
            contentPadding = androidx.compose.foundation.layout.PaddingValues(),
        )
    }
}

```

## app\src\main\java\com\example\pr18\utils\AppLogger.kt

```kotlin
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


```

## app\src\main\java\com\example\pr18\MainActivity.kt

```kotlin
package com.example.pr18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pr18.ui.Pr18App
import com.example.pr18.ui.theme.Pr18Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr18Theme {
                Pr18App()
            }
        }
    }
}

```

## app\src\main\res\drawable\ic_launcher_background.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path
        android:fillColor="#3DDC84"
        android:pathData="M0,0h108v108h-108z" />
    <path
        android:fillColor="#00000000"
        android:pathData="M9,0L9,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,0L19,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M29,0L29,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M39,0L39,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M49,0L49,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M59,0L59,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M69,0L69,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M79,0L79,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M89,0L89,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M99,0L99,108"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,9L108,9"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,19L108,19"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,29L108,29"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,39L108,39"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,49L108,49"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,59L108,59"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,69L108,69"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,79L108,79"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,89L108,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M0,99L108,99"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,29L89,29"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,39L89,39"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,49L89,49"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,59L89,59"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,69L89,69"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M19,79L89,79"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M29,19L29,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M39,19L39,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M49,19L49,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M59,19L59,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M69,19L69,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
    <path
        android:fillColor="#00000000"
        android:pathData="M79,19L79,89"
        android:strokeWidth="0.8"
        android:strokeColor="#33FFFFFF" />
</vector>

```

## app\src\main\res\drawable\ic_launcher_foreground.xml

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path android:pathData="M31,63.928c0,0 6.4,-11 12.1,-13.1c7.2,-2.6 26,-1.4 26,-1.4l38.1,38.1L107,108.928l-32,-1L31,63.928z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:endX="85.84757"
                android:endY="92.4963"
                android:startX="42.9492"
                android:startY="49.59793"
                android:type="linear">
                <item
                    android:color="#44000000"
                    android:offset="0.0" />
                <item
                    android:color="#00000000"
                    android:offset="1.0" />
            </gradient>
        </aapt:attr>
    </path>
    <path
        android:fillColor="#FFFFFF"
        android:fillType="nonZero"
        android:pathData="M65.3,45.828l3.8,-6.6c0.2,-0.4 0.1,-0.9 -0.3,-1.1c-0.4,-0.2 -0.9,-0.1 -1.1,0.3l-3.9,6.7c-6.3,-2.8 -13.4,-2.8 -19.7,0l-3.9,-6.7c-0.2,-0.4 -0.7,-0.5 -1.1,-0.3C38.8,38.328 38.7,38.828 38.9,39.228l3.8,6.6C36.2,49.428 31.7,56.028 31,63.928h46C76.3,56.028 71.8,49.428 65.3,45.828zM43.4,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2c-0.3,-0.7 -0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C45.3,56.528 44.5,57.328 43.4,57.328L43.4,57.328zM64.6,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2s-0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C66.5,56.528 65.6,57.328 64.6,57.328L64.6,57.328z"
        android:strokeWidth="1"
        android:strokeColor="#00000000" />
</vector>
```

## app\src\main\res\mipmap-anydpi\ic_launcher.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background" />
    <foreground android:drawable="@drawable/ic_launcher_foreground" />
    <monochrome android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
```

## app\src\main\res\mipmap-anydpi\ic_launcher_round.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background" />
    <foreground android:drawable="@drawable/ic_launcher_foreground" />
    <monochrome android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
```

## app\src\main\res\values\colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```

## app\src\main\res\values\strings.xml

```xml
<resources>
    <string name="app_name">pr18</string>
</resources>
```

## app\src\main\res\values\themes.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="Theme.Pr18" parent="android:Theme.Material.Light.NoActionBar" />
</resources>
```

## app\src\main\res\xml\backup_rules.xml

```xml
<?xml version="1.0" encoding="utf-8"?><!--
   Sample backup rules file; uncomment and customize as necessary.
   See https://developer.android.com/guide/topics/data/autobackup
   for details.
   Note: This file is ignored for devices older than API 31
   See https://developer.android.com/about/versions/12/backup-restore
-->
<full-backup-content>
    <!--
   <include domain="sharedpref" path="."/>
   <exclude domain="sharedpref" path="device.xml"/>
-->
</full-backup-content>
```

## app\src\main\res\xml\data_extraction_rules.xml

```xml
<?xml version="1.0" encoding="utf-8"?><!--
   Sample data extraction rules file; uncomment and customize as necessary.
   See https://developer.android.com/about/versions/12/backup-restore#xml-changes
   for details.
-->
<data-extraction-rules>
    <cloud-backup>
        <!-- TODO: Use <include> and <exclude> to control what is backed up.
        <include .../>
        <exclude .../>
        -->
    </cloud-backup>
    <!--
    <device-transfer>
        <include .../>
        <exclude .../>
    </device-transfer>
    -->
</data-extraction-rules>
```

## app\src\main\AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Pr18">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.Pr18">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <service
            android:name=".service.simple.SimpleService"
            android:exported="false" />

        <service
            android:name=".service.stopself.StopServiceDemo"
            android:exported="false" />

        <service
            android:name=".service.sticky.StickyDemoService"
            android:exported="false" />

        <service
            android:name=".service.pending.PendingIntentDemoService"
            android:exported="false" />

        <receiver
            android:name=".service.pending.PendingResultReceiver"
            android:exported="false" />
    </application>

</manifest>

```

## app\src\test\java\com\example\pr18\model\ServiceRunningStateTest.kt

```kotlin
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


```

## app\src\test\java\com\example\pr18\service\pending\PendingTasksRepositoryTest.kt

```kotlin
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


```

## app\src\test\java\com\example\pr18\ExampleUnitTest.kt

```kotlin
package com.example.pr18

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
```

## app\.gitignore

```
/build
```

## app\build.gradle.kts

```
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.pr18"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.pr18"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

```

## app\proguard-rules.pro

```
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
```

## gradle\wrapper\gradle-wrapper.properties

```
#Sun May 17 16:26:43 NOVT 2026
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionSha256Sum=a17ddd85a26b6a7f5ddb71ff8b05fc5104c0202c6e64782429790c933686c806
distributionUrl=https\://services.gradle.org/distributions/gradle-9.1.0-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists

```

## gradle\libs.versions.toml

```
[versions]
agp = "9.0.1"
coreKtx = "1.18.0"
junit = "4.13.2"
junitVersion = "1.3.0"
espressoCore = "3.7.0"
lifecycleRuntimeKtx = "2.10.0"
activityCompose = "1.13.0"
kotlin = "2.0.21"
composeBom = "2024.09.00"
navigationCompose = "2.9.0"
kotlinxCoroutines = "1.8.1"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-lifecycle-runtime-compose = { group = "androidx.lifecycle", name = "lifecycle-runtime-compose", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-compose-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-compose-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-compose-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-compose-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-compose-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-compose-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
kotlinx-coroutines-test = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-test", version.ref = "kotlinxCoroutines" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }

```

## .gitignore

```
*.iml
.gradle
/local.properties
/.idea/caches
/.idea/libraries
/.idea/modules.xml
/.idea/workspace.xml
/.idea/navEditor.xml
/.idea/assetWizardSettings.xml
.DS_Store
/build
/captures
.externalNativeBuild
.cxx
local.properties

```

## build.gradle.kts

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
```

## gradle.properties

```
# Project-wide Gradle settings.
# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.
# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html
# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. For more details, visit
# https://developer.android.com/r/tools/gradle-multi-project-decoupled-projects
# org.gradle.parallel=true
# AndroidX package structure to make it clearer which packages are bundled with the
# Android operating system, and which are packaged with your app's APK
# https://developer.android.com/topic/libraries/support-library/androidx-rn
android.useAndroidX=true
# Kotlin code style for this project: "official" or "obsolete":
kotlin.code.style=official
# Enables namespacing of each library's R class so that its R class includes only the
# resources declared in the library itself and none from the library's dependencies,
# thereby reducing the size of the R class for that library
android.nonTransitiveRClass=true
```

## gradlew

```
#!/bin/sh

#
# Copyright © 2015 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# SPDX-License-Identifier: Apache-2.0
#

##############################################################################
#
#   Gradle start up script for POSIX generated by Gradle.
#
#   Important for running:
#
#   (1) You need a POSIX-compliant shell to run this script. If your /bin/sh is
#       noncompliant, but you have some other compliant shell such as ksh or
#       bash, then to run this script, type that shell name before the whole
#       command line, like:
#
#           ksh Gradle
#
#       Busybox and similar reduced shells will NOT work, because this script
#       requires all of these POSIX shell features:
#         * functions;
#         * expansions «$var», «${var}», «${var:-default}», «${var+SET}»,
#           «${var#prefix}», «${var%suffix}», and «$( cmd )»;
#         * compound commands having a testable exit status, especially «case»;
#         * various built-in commands including «command», «set», and «ulimit».
#
#   Important for patching:
#
#   (2) This script targets any POSIX shell, so it avoids extensions provided
#       by Bash, Ksh, etc; in particular arrays are avoided.
#
#       The "traditional" practice of packing multiple parameters into a
#       space-separated string is a well documented source of bugs and security
#       problems, so this is (mostly) avoided, by progressively accumulating
#       options in "$@", and eventually passing that to Java.
#
#       Where the inherited environment variables (DEFAULT_JVM_OPTS, JAVA_OPTS,
#       and GRADLE_OPTS) rely on word-splitting, this is performed explicitly;
#       see the in-line comments for details.
#
#       There are tweaks for specific operating systems such as AIX, CygWin,
#       Darwin, MinGW, and NonStop.
#
#   (3) This script is generated from the Groovy template
#       https://github.com/gradle/gradle/blob/HEAD/platforms/jvm/plugins-application/src/main/resources/org/gradle/api/internal/plugins/unixStartScript.txt
#       within the Gradle project.
#
#       You can find Gradle at https://github.com/gradle/gradle/.
#
##############################################################################

# Attempt to set APP_HOME

# Resolve links: $0 may be a link
app_path=$0

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}  # leaves a trailing /; empty if no leading path
    [ -h "$app_path" ]
do
    ls=$( ls -ld "$app_path" )
    link=${ls#*' -> '}
    case $link in             #(
      /*)   app_path=$link ;; #(
      *)    app_path=$APP_HOME$link ;;
    esac
done

# This is normally unused
# shellcheck disable=SC2034
APP_BASE_NAME=${0##*/}
# Discard cd standard output in case $CDPATH is set (https://github.com/gradle/gradle/issues/25036)
APP_HOME=$( cd -P "${APP_HOME:-./}" > /dev/null && printf '%s\n' "$PWD" ) || exit

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD=maximum

warn () {
    echo "$*"
} >&2

die () {
    echo
    echo "$*"
    echo
    exit 1
} >&2

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "$( uname )" in                #(
  CYGWIN* )         cygwin=true  ;; #(
  Darwin* )         darwin=true  ;; #(
  MSYS* | MINGW* )  msys=true    ;; #(
  NONSTOP* )        nonstop=true ;;
esac

CLASSPATH="\\\"\\\""


# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD=$JAVA_HOME/jre/sh/java
    else
        JAVACMD=$JAVA_HOME/bin/java
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD=java
    if ! command -v java >/dev/null 2>&1
    then
        die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
fi

# Increase the maximum file descriptors if we can.
if ! "$cygwin" && ! "$darwin" && ! "$nonstop" ; then
    case $MAX_FD in #(
      max*)
        # In POSIX sh, ulimit -H is undefined. That's why the result is checked to see if it worked.
        # shellcheck disable=SC2039,SC3045
        MAX_FD=$( ulimit -H -n ) ||
            warn "Could not query maximum file descriptor limit"
    esac
    case $MAX_FD in  #(
      '' | soft) :;; #(
      *)
        # In POSIX sh, ulimit -n is undefined. That's why the result is checked to see if it worked.
        # shellcheck disable=SC2039,SC3045
        ulimit -n "$MAX_FD" ||
            warn "Could not set maximum file descriptor limit to $MAX_FD"
    esac
fi

# Collect all arguments for the java command, stacking in reverse order:
#   * args from the command line
#   * the main class name
#   * -classpath
#   * -D...appname settings
#   * --module-path (only if needed)
#   * DEFAULT_JVM_OPTS, JAVA_OPTS, and GRADLE_OPTS environment variables.

# For Cygwin or MSYS, switch paths to Windows format before running java
if "$cygwin" || "$msys" ; then
    APP_HOME=$( cygpath --path --mixed "$APP_HOME" )
    CLASSPATH=$( cygpath --path --mixed "$CLASSPATH" )

    JAVACMD=$( cygpath --unix "$JAVACMD" )

    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    for arg do
        if
            case $arg in                                #(
              -*)   false ;;                            # don't mess with options #(
              /?*)  t=${arg#/} t=/${t%%/*}              # looks like a POSIX filepath
                    [ -e "$t" ] ;;                      #(
              *)    false ;;
            esac
        then
            arg=$( cygpath --path --ignore --mixed "$arg" )
        fi
        # Roll the args list around exactly as many times as the number of
        # args, so each arg winds up back in the position where it started, but
        # possibly modified.
        #
        # NB: a `for` loop captures its iteration list before it begins, so
        # changing the positional parameters here affects neither the number of
        # iterations, nor the values presented in `arg`.
        shift                   # remove old arg
        set -- "$@" "$arg"      # push replacement arg
    done
fi


# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Collect all arguments for the java command:
#   * DEFAULT_JVM_OPTS, JAVA_OPTS, and optsEnvironmentVar are not allowed to contain shell fragments,
#     and any embedded shellness will be escaped.
#   * For example: A user cannot expect ${Hostname} to be expanded, as it is an environment variable and will be
#     treated as '${Hostname}' itself on the command line.

set -- \
        "-Dorg.gradle.appname=$APP_BASE_NAME" \
        -classpath "$CLASSPATH" \
        -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
        "$@"

# Stop when "xargs" is not available.
if ! command -v xargs >/dev/null 2>&1
then
    die "xargs is not available"
fi

# Use "xargs" to parse quoted args.
#
# With -n1 it outputs one arg per line, with the quotes and backslashes removed.
#
# In Bash we could simply go:
#
#   readarray ARGS < <( xargs -n1 <<<"$var" ) &&
#   set -- "${ARGS[@]}" "$@"
#
# but POSIX shell has neither arrays nor command substitution, so instead we
# post-process each arg (as a line of input to sed) to backslash-escape any
# character that might be a shell metacharacter, then use eval to reverse
# that process (while maintaining the separation between arguments), and wrap
# the whole thing up as a single "set" statement.
#
# This will of course break if any of these variables contains a newline or
# an unmatched quote.
#

eval "set -- $(
        printf '%s\n' "$DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS" |
        xargs -n1 |
        sed ' s~[^-[:alnum:]+,./:=@_]~\\&~g; ' |
        tr '\n' ' '
    )" '"$@"'

exec "$JAVACMD" "$@"

```

## gradlew.bat

```
@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=


@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" -jar "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%GRADLE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

```

## settings.gradle.kts

```
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pr18"
include(":app")

```

## ПЗ.18 Приложение с использованием сервисов.docx

```
[ERROR] The process cannot access the file 'C:\Users\ivank\AndroidStudioProjects\pr18\ПЗ.18 Приложение с использованием сервисов.docx' because it is being used by another process.
```

