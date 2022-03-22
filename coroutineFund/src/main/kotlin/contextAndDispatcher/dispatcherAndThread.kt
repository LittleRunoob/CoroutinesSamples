package contextAndDispatcher

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    // 协程上下文与调度器
    // 调度器与协程
    launch { // 运行在父协程的上下文中，即 runBlocking 主协程
        println("main runBlocking : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // 将会获取默认调度器
        println("Default : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }

    // 非受限调度器 vs 受限调度器
    /**
     * 非受限的调度器是一种高级机制，可以在某些极端情况下提供帮助而不需要调度协程以便稍后执行或产生 不希望的副作用，因为某些操作必须立即在协程中执行。非受限调度器不应该在通常的代码中使用。
     */
    launch(Dispatchers.Unconfined) { // 非受限的——将和主线程一起工作
        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // 父协程的上下文，主 runBlocking 协程
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}