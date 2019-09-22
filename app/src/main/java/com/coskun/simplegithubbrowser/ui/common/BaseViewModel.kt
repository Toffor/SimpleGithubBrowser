package com.coskun.simplegithubbrowser.ui.common

import androidx.lifecycle.ViewModel
import com.coskun.simplegithubbrowser.util.launchSafe
import com.coskun.simplegithubbrowser.util.logError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    protected fun launch(
        onError: (Throwable) -> Unit = ::onViewModelScopeError,
        onSuccess: suspend () -> Unit
    ) = viewModelScope.launchSafe(onError = onError, onSuccess = onSuccess)


    protected open fun onViewModelScopeError(throwable: Throwable) {
        logError(throwable, this.javaClass.simpleName)
    }


}