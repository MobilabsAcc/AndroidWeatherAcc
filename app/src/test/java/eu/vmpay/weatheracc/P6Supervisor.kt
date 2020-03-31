package eu.vmpay.weatheracc

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.Test

class P6Supervisor {
    @Test
    fun supervisorJobTest() = runBlocking {
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            launch(CoroutineExceptionHandler { _, _ -> }) {
                delay(1000)
                throw AssertionError("Cancelled")
            }
            launch {
                delay(2000)
                println("AAA")
            }
        }
        supervisor.join()
    }

    @Test
    fun supervisorScopeTest() = runBlocking<Unit> {
        supervisorScope {
            launch(CoroutineExceptionHandler { _, _ -> }) {
                delay(1000)
                throw AssertionError("Cancelled")
            }
            launch {
                delay(2000)
                println("AAA")
            }
        }
    }
}
