package eu.vmpay.weatheracc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class P8FlowAdv {
    @Test
    fun flowOnTest() = runBlocking {
        val UI = newSingleThreadContext("UI")
        val IO = newSingleThreadContext("IO")
        fun logThread(taskName: String) {
            println("Doing $taskName on ${Thread.currentThread().name}")
        }
        withContext(UI) {
            val singleValue = flow { logThread("flow"); emit("A") } // will be executed on IO if context wasn't specified before
                    .map { logThread("map"); it } // Will be executed in IO
                    .flowOn(IO)
                    .filter { logThread("filter"); it != null } // Will be executed in Default
                    .flowOn(Dispatchers.Default)
                    .collect { logThread("collect") }
        }
    }

    @Test
    fun flowOnStart() = runBlocking<Unit> {
        flow {
            (1..5).forEach {
                delay(1000)
                emit(it)
                if (it == 2) throw RuntimeException("Error on $it")
            }
        }.onEach { println("On each $it") }
                .onStart { println("Starting flow") }
                .onCompletion { println("Flow completed") }
                .catch { ex -> println("Exception message: ${ex.message}") }
                .toList()
    }


}
