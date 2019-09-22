package com.coskun.simplegithubbrowser

import android.app.Application
import com.coskun.simplegithubbrowser.di.ApplicationComponent
import com.coskun.simplegithubbrowser.di.DaggerApplicationComponent

class GithubApp : Application() {


    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}