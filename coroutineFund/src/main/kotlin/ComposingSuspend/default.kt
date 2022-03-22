package ComposingSuspend

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    //default
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
    //使用轻量化的线程将协程并发
    val timeAsync = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Async Completed in $timeAsync ms")
    //惰性启动，当await或者start的时候才调用函数
    val timeLazy = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() } // 执行一些计算
        two.start() // 启动第一个
        one.start() // 启动第二个
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $timeLazy ms")
    //使用async的结构化并发
    val timeStructureAsync = measureTimeMillis { concurrentSum() }
    println("Completed in $timeStructureAsync ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    println("one")
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    println("two")
    return 29
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}