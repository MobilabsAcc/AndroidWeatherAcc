package eu.vmpay.weatheracc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class P1HelloWorld {

    @Test
    fun helloWorldTest() {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        Thread.sleep(2000L)
    }

    @Test
    fun helloWorldPartBlockingTest() {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        runBlocking {
            delay(2000L)
        }
    }

    @Test
    fun helloWorldFullBlockingTest() = runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        delay(2000L)
    }
}
