package com.coskun.simplegithubbrowser.di

import androidx.fragment.app.FragmentActivity
import com.coskun.simplegithubbrowser.GithubApp
import com.coskun.simplegithubbrowser.ui.common.BaseFragment

object ComponentFactory {

    fun getApplicationComponent(activity: FragmentActivity): ApplicationComponent {
        return (activity.application as GithubApp).applicationComponent
    }

    fun getPresentationComponent(fragment: BaseFragment): PresentationComponent {
        return getApplicationComponent(fragment.activity!!).newPresentationComponent()
    }
}