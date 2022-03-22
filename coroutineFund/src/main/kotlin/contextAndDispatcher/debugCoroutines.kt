package contextAndDispatcher

import kotlinx.coroutines.async
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
//    val a = async {
//        println("I'm computing a piece of the answer")
//        6
//    }
//    val b = async {
//        println("I'm computing another piece of the answer")
//        7
//    }
//    println("The answer is ${a.await() * b.await()}")
    // 协程在不同线程间跳转
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                println("${this.coroutineContext}Started in ctx1")
                withContext(ctx2) {
                    println("${this.coroutineContext}Working in ctx2")
                }
                println("${this.coroutineContext}Back to ctx1")
            }
        }
    }
}