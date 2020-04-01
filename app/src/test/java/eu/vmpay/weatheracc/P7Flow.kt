package eu.vmpay.weatheracc

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

class P7Flow {
    @Test
    fun flowBuilderTest() = runBlocking<Unit> {
        println("Started producing")
        val channel = flow<Int> {
            println("Flow started")
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }

        delay(100)
        println("Calling flow...")
        channel.collect { value -> println(value) }
        println("Consuming again...")
        channel.collect { value -> println(value) }
    }

    @Test
    fun flowOfTest() = runBlocking<Unit> {
        flowOf(1, 2, 3)
                .onEach { print(it) }
                .map { it * 10 }
                .collect { println(" map -> $it") }
    }

    @Test
    fun flatMapTest() = runBlocking {
        measureTimeMillis {
            ('A'..'C').asFlow()
                    .flatMapMerge { flowFrom(it) }
                    .collect { print(it) }
        }.let(::print)
    }

    private fun flowFrom(elem: Any) = flowOf(0, 1, 2)
            .onEach { delay(1000) }
            .map { "${it}/${elem} " }

    @Test
    fun flatMapLatest() = runBlocking {
        measureTimeMillis {
            ('A'..'C').asFlow()
                    .onEach { delay(1500) }
                    .flatMapLatest { flowFrom(it) }
                    .collect { print(it) } // 0/C 0/C 1/C 2/C
        }.let(::print)
    }
}
