import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
    coroutineScope { // 创建一个协程作用域
        launch {// 协程构造器
            //挂起lambda表达式
//            delay(500L)
//            println("Task from nested launch")
            //挂起函数
            nestedLaunch()
        }
        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }
    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}

suspend fun nestedLaunch() {
    delay(500L)
    println("Task from nested launch")
}