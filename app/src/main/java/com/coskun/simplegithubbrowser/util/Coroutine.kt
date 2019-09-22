package com.coskun.simplegithubbrowser.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Similar to [launch] only difference wraps result in a try catch block and takes two separate
 * [onError] and [onSuccess] params for easy usage.
 */
fun CoroutineScope.launchSafe(
    onError: (Throwable) -> Unit = {},
    onSuccess: suspend () -> Unit
) = launch {
    try {
        onSuccess()
    } catch (e: Exception) {
        onError(e)
    }
}
