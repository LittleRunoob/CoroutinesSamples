import kotlinx.coroutines.*
import kotlin.concurrent.thread
//多协程
fun main() = runBlocking {
    // 单个实例
    repeat(100_000) { // 启动大量的协程
        launch {
            delay(5000L)
            print(".")
        }
    }
}

// 多线程 OOM
//fun main () {
//    repeat(100_000) {
//        thread {
//            Thread.sleep(5000L)
//            print(".")
//        }
//    }
//}

