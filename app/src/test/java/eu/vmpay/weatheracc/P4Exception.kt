package eu.vmpay.weatheracc

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class P4Exception {

    @Test
    fun exceptionTest() = runBlocking {
        println(calculateValue())
    }

    private suspend fun calculateValue(): String {
        val first = GlobalScope.async {
            delay(1000)
            println("I am done")
            "First"
        }
        val second = GlobalScope.async {
            delay(100)
            if (Random.nextBoolean()) throw Error() else "Second"
        }
        return "Tasks done: ${first.await()} ${second.await()}"
    }

    @Test
    fun exceptionHandlerTest() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        val job = GlobalScope.launch(handler) {
            delay(1500)
            throw AssertionError()
        }
        job.join() // Caught java.lang.AssertionError
    }


}