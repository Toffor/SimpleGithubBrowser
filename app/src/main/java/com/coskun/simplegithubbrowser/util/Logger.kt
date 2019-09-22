package com.coskun.simplegithubbrowser.util

import android.util.Log
import com.coskun.simplegithubbrowser.BuildConfig

private const val TAG = "SimpleGithubBrowser."

private val DEBUG = BuildConfig.DEBUG

fun logDebug(any: Any?) {

    if (!DEBUG) return

    val element = Thread.currentThread().stackTrace.getOrNull(3) ?: return

    val result = StringBuilder()

    result.append(TAG)

    val fileName = element.fileName
    val lineNumber = element.lineNumber

    if (lineNumber >= 0 && fileName != null) {
        result.append("(", fileName, ":", lineNumber, ")")
    }

    Log.d(result.toString(), any.toString())

}


fun logError(throwable: Throwable?, message: String? = null) {

    if (!DEBUG) return

    val element = Thread.currentThread().stackTrace.getOrNull(3) ?: return

    val result = StringBuilder()

    result.append(TAG)

    val fileName = element.fileName
    val lineNumber = element.lineNumber

    if (lineNumber >= 0 && fileName != null) {
        result.append("(").append(fileName).append(":").append(lineNumber).append(")")
    }

    Log.e(result.toString(), message ?: throwable?.message, throwable)
}

