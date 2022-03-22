import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
    // 回调回调回调
    // 尝试将耗时函数的回调写法利用协程转换为挂起函数
    getUserInfo(object : Callback {
        override fun onSuccess(response: String) {
            println(response)
            getFriendList(response, object :Callback {
                override fun onSuccess(response: String) {
                    println(response)
                    getFriendFeed(response, object :Callback {
                        override fun onSuccess(response: String) {
                            println(response)
                        }
                    })
                }
            })
        }
    })
    // 协程

    runBlocking {
        val user = getUserInfoSus()
        val friendList = getFriendListSus(user)
        val feedList = getFeedListSus(friendList)
    }
}

fun getUserInfo(callback: Callback) {
    Thread.sleep(1000L)
    callback.onSuccess("User")
}

fun getFriendList(user: String, callback: Callback) {
    Thread.sleep(1000L)
    callback.onSuccess("$user friendList")
}

fun getFriendFeed(friendList: String, callback: Callback) {
    Thread.sleep(1000L)
    callback.onSuccess("$friendList feed")
}

suspend fun getUserInfoSus(): String = suspendCoroutine { cont ->
    getUserInfo(object :Callback {
        override fun onSuccess(response: String) {
            println("SUS user")
            cont.resume(response)
        }
    })
}

suspend fun getFriendListSus(user: String): String = suspendCoroutine { cont ->
    getFriendList(user, object :Callback {
        override fun onSuccess(response: String) {
            println("SUS $user friendList")
            cont.resume(response)
        }
    })
}

suspend fun getFeedListSus(friendList: String): String = suspendCoroutine { cont ->
    getFriendList(friendList, object :Callback {
        override fun onSuccess(response: String) {
            println("SUS $friendList feed")
            cont.resume(response)
        }
    })
}

interface Callback {
    fun onSuccess(response: String)
}