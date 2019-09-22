package com.coskun.simplegithubbrowser.ui.navigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.coskun.simplegithubbrowser.MainActivity
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.di.AppScope
import com.coskun.simplegithubbrowser.ui.userrepos.UserReposFragment
import com.coskun.simplegithubbrowser.ui.common.BaseFragment
import com.coskun.simplegithubbrowser.util.commitTransaction
import javax.inject.Inject

@AppScope
class Navigator @Inject constructor() {

    @IdRes
    private val containerId = R.id.frameMainContainer

    private var fragmentManager: FragmentManager? = null

    private val primaryNavigationFragment: BaseFragment?
        get() = fragmentManager?.primaryNavigationFragment as? BaseFragment


    fun initialize(activity: MainActivity, savedInstanceState: Bundle?) {
        fragmentManager = activity.supportFragmentManager
        if (savedInstanceState == null) {
            navigateToUserRepos()
        }
    }


    fun navigateToUserRepos() {
        navigateInternal(UserReposFragment())
    }

    fun onBackPressed() {
        primaryNavigationFragment?.onBackPressed()
    }

    fun popBack() {
        fragmentManager?.takeIf { it.backStackEntryCount > 1 }?.popBackStack()
    }

    private fun navigateInternal(fragment: BaseFragment, addToBackStack: Boolean = true) {
        fragmentManager.commitTransaction {
            replace(containerId, fragment)
            setPrimaryNavigationFragment(fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}