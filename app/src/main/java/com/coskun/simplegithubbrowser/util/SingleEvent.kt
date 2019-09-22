package com.coskun.simplegithubbrowser.util

data class SingleEvent<T : Any?>(private var _data: T? = null) {

    fun getContentIfNotHandled(): T? {
        if (_data == null) return null
        val data = _data
        _data = null
        return data
    }
}