package com.coskun.simplegithubbrowser.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.coskun.simplegithubbrowser.di.ComponentFactory
import com.coskun.simplegithubbrowser.di.PresentationComponent
import com.coskun.simplegithubbrowser.ui.common.BaseFragment


/**
 * Creates a [Bundle] object and passes as reference to [apply] function
 * and sets given bundle to [this] fragment as arguments.
 */
fun Fragment.withArguments(apply: Bundle.() -> Unit) = this.apply {
    arguments = Bundle().apply(apply)
}

/**
 * Observes a given [liveData] object and applies [action]
 * when live data value change and it is not null
 */
inline fun <T : Any> Fragment.observe(liveData: LiveData<T>, crossinline action: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { it?.let(action) })
}


/**
 * Similar to [observe] only difference this functions observes for an [SingleEvent]
 */
fun <T : Any> Fragment.observeSingleEvent(
    liveData: LiveData<SingleEvent<T?>>,
    action: (T) -> Unit
) = liveData.observeSingleEvent(viewLifecycleOwner, action)


/**
 * A delegate property which creates an lazy instance of [PresentationComponent]
 */
fun BaseFragment.presentationComponents() = lazy(LazyThreadSafetyMode.NONE) {
    ComponentFactory.getPresentationComponent(this)
}

