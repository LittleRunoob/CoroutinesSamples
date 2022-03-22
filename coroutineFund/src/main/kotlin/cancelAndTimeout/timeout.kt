package cancelAndTimeout

import kotlinx.coroutines.*

fun main() = runBlocking {

    val result = try {
        // withTimeoutOrNull
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // 在它运行得到结果之前取消它
        }
    } catch (e: TimeoutCancellationException) {
        "Timeout"
    }
    println("Result is $result")
}