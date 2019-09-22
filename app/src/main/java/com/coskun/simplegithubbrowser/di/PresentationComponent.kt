package com.coskun.simplegithubbrowser.di

import com.coskun.simplegithubbrowser.ui.navigator.Navigator
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun getNavigator(): Navigator
}