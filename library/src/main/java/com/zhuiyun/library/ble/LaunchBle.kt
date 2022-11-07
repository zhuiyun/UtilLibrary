package com.zhuiyun.library.ble

import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive

/**

 * @author: yun
 * @date: 2022/11/7 0007
 */

private val coroutineScope: CoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

fun CoroutineScope.launchBle(
    block: suspend CoroutineScope.() -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onFinally: (() -> Unit)? = null
): Job {
    return coroutineScope.launch {
        try {
            coroutineScope {
                onStart?.invoke()
                block()
            }
        } catch (e: Throwable) {
            if (onError != null && isActive) {
                try {
                    onError(e)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            } else {
                e.printStackTrace()
            }
        } finally {
            onFinally?.invoke()
        }
    }
}