package com.coskun.simplegithubbrowser.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coskun.simplegithubbrowser.util.presentationComponents

abstract class BaseFragment : Fragment() {

    val presentationComponent by presentationComponents()

    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onViewAppear(savedInstanceState)
    }

    protected abstract fun onViewAppear(savedInstanceState: Bundle?)

    fun onBackPressed() {
        presentationComponent.getNavigator().popBack()
    }
}

