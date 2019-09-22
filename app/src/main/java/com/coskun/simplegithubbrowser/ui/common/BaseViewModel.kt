package com.coskun.simplegithubbrowser.ui.common

import android.util.SparseArray
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coskun.simplegithubbrowser.ui.common.model.ErrorStatus
import com.coskun.simplegithubbrowser.util.launchSafe
import com.coskun.simplegithubbrowser.util.logError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel : ViewModel() {

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    private val activeJobsMap = SparseArray<Job>()

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    private val _errorStatus = MutableLiveData<ErrorStatus>()
    val errorStatus: LiveData<ErrorStatus> = _errorStatus

    protected fun cancelPreviousAndLaunch(
        requestId: Int = 0,
        onError: (Throwable) -> Unit = ::onViewModelScopeError,
        onSuccess: suspend () -> Unit
    ) {
        val job = activeJobsMap[requestId]
        job?.cancel()
        activeJobsMap.put(requestId, launch(onError, onSuccess))
    }

    protected fun launch(
        onError: (Throwable) -> Unit = ::onViewModelScopeError,
        onSuccess: suspend () -> Unit
    ) = viewModelScope.launchSafe(onError = onError, onSuccess = onSuccess)

    protected fun updateLoadingStatus(isLoading: Boolean, requestCode: Int = 0) {
        _loadingStatus.value = LoadingStatus(isLoading, requestCode)
    }

    protected fun updateErrorStatus(
        @StringRes errorMessage: Int,
        @StringRes actionMessage: Int = 0,
        errorCode: Int = 0
    ) {
        _errorStatus.value = ErrorStatus(errorMessage, actionMessage, errorCode)
    }

    protected open fun onViewModelScopeError(throwable: Throwable) {
        logError(throwable, this.javaClass.simpleName)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
        activeJobsMap.clear()
    }


}