package com.coskun.simplegithubbrowser.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Creates SingleEvent from [data] end sets as value.
 */
fun <T : Any> MutableLiveData<SingleEvent<T?>>.sendEvent(data: T?) {
    value = SingleEvent(data)
}


/**
 * Consumes value if value has not consumed before and calls [action] with value as param.
 */
fun <T : Any> LiveData<SingleEvent<T?>>.observeSingleEvent(
    lifecycleOwner: LifecycleOwner,
    action: (T) -> Unit
) {
    observe(lifecycleOwner, SingleEventObserver(action))
}


/**
 * If current values is not null applies [transform] function to value and set value as the new value.
 */
inline fun <T : Any?> MutableLiveData<T>.update(transform: (T) -> T) {
    value = value?.let(transform) ?: return
}




/**
 * Executes given [action] if current value is not null and passing current valueas param.
 */
fun <T> LiveData<T>.executeIfValueNotNull(action: (T) -> Unit) {
    action(value ?: return)
}


/**
 * If current value is not null returns current value else returns [defaultValue]
 */
fun <T> LiveData<T>.getValueOrDefault(defaultValue: T): T {
    return value ?: defaultValue
}


/**
 * Similar to [getValueOrDefault] only difference, applies [transform] function to value and returns
 * transformed value, else if value is null returns [defaultValue]
 */
fun <T, R> LiveData<T>.getValueOrDefault(defaultValue: R, transform: (T) -> R): R {
    return value?.let(transform) ?: defaultValue
}
