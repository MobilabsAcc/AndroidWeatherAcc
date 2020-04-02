package eu.vmpay.weatheracc

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class P5Scope {

    @Test
    fun basicScopeTest() = runBlocking {
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope {
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope")
        }

        println("Coroutine scope is over")
    }

    @Test
    fun funScopeTest() = runBlocking {
        val value = makeAsyncCalculationsInCoroutineScope()
        println(value)
    }

    private suspend fun makeAsyncCalculationsInCoroutineScope(): String = coroutineScope {
        val one = async {
            delay(200)
            1
        }
        val two = async {
            delay(100)
            2
        }
        "The answer is ${one.await() + two.await()}"
    }
}
