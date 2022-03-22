import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    GlobalScope.launch {
//        //特殊的挂起函数，不会造成线程阻塞，只能在协程中使用
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    //线程阻塞
//    Thread.sleep(2000L)
//}

// 显式调用runBlocking协程构造器来delay
fun main() {
    MyTest().testSuspendingFunction()
    // 顶层协程 -> 作用域
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("World!")
    }
    println("Hello,") // 主线程中的代码会立即执行
    runBlocking { // 但是这个表达式阻塞了主线程
        delay(2000L) // ......我们延迟 2 秒来保证 JVM 的存活
    }
}

class MyTest{
    fun testSuspendingFunction() = runBlocking<Unit> {
//        delay(2000L)
//        println("testFunction")
        // 显式等待一个协程运行
//        val job =
        GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("World1!")
        }
//        job.join()
        println("Hello1,")
        //job.join() // 等待直到子协程执行结束
    }
}