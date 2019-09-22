package com.coskun.simplegithubbrowser.util

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.di.ComponentFactory
import com.coskun.simplegithubbrowser.di.PresentationComponent
import com.coskun.simplegithubbrowser.ui.common.BaseFragment
import com.coskun.simplegithubbrowser.ui.common.BaseViewModel
import com.coskun.simplegithubbrowser.ui.common.model.ErrorStatus
import com.google.android.material.snackbar.Snackbar


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


/**
 * A lazy delegation of viewModel which's owner parent activity.
 */
inline fun <reified VM : BaseViewModel> BaseFragment.parentActivityViewModels() =
    lazy(mode = LazyThreadSafetyMode.NONE) {
        val factory = presentationComponent.getViewModelFactory()
        ViewModelProvider(activity!!, factory).get(VM::class.java)
    }


/**
 * Shows [Snackbar] according to given [ErrorStatus] object.
 */
fun Fragment.showSnackbar(errorStatus: ErrorStatus, action: (View) -> Unit = {}) {
    val snackbar = Snackbar.make(view!!, errorStatus.errorMessage, Snackbar.LENGTH_INDEFINITE)
    if (errorStatus.actionMessage != 0) {
        snackbar.setAction(errorStatus.actionMessage, action)
    }
    snackbar.setBackgroundTint(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
    snackbar.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite70))
    snackbar.setActionTextColor(ContextCompat.getColor(context!!, R.color.colorAccent))
    snackbar.show()
}