package eu.vmpay.weatheracc

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class P2AsynJob {

    fun CoroutineScope.log(msg: String) = println("[${coroutineContext[CoroutineName]?.name}] $msg")

    @Test
    fun asyncWorldTest() = runBlocking(CoroutineName("main")) {
        log("Started main coroutine")
        val v1 = async(CoroutineName("firstCoroutine")) {
            delay(500)
            log("Computing first")
            "Async"
        }
        val v2 = async(CoroutineName("secondCoroutine")) {
            delay(1000)
            log("Computing second")
            "World"
        }
        log("Hello, ${v1.await()} ${v2.await()}")
    }

    @Test
    fun jobTest() = runBlocking {
        val job = Job()
        launch(job) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }

    @Test
    fun jobJoinTest() = runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }
}
