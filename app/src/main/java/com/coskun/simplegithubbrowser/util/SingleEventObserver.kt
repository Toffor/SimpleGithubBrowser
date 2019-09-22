package com.coskun.simplegithubbrowser.util

import androidx.lifecycle.Observer

class SingleEventObserver<T : Any>(private val onEventAction: (T) -> Unit) :
    Observer<SingleEvent<T?>> {

    override fun onChanged(event: SingleEvent<T?>) {
        event.getContentIfNotHandled()?.let(onEventAction)
    }

}