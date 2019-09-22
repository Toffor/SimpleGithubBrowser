package com.coskun.simplegithubbrowser.util

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Applies [body] if [this] [FragmentManager] is not null.
 */
fun FragmentManager?.commitTransaction(body: FragmentTransaction.() -> Unit) {
    if (this == null || isStateSaved) return
    val transaction = beginTransaction()
    transaction.body()
    transaction.commit()
}

