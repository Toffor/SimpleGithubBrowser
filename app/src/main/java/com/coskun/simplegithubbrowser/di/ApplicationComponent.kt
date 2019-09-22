package com.coskun.simplegithubbrowser.di

import com.coskun.simplegithubbrowser.GithubApp
import com.coskun.simplegithubbrowser.ui.navigator.Navigator
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getNavigator(): Navigator

    fun newPresentationComponent() : PresentationComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: GithubApp): ApplicationComponent
    }
}