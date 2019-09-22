package com.coskun.simplegithubbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coskun.simplegithubbrowser.di.ComponentFactory
import com.coskun.simplegithubbrowser.ui.navigator.Navigator

class MainActivity : AppCompatActivity() {

    private val applicationComponent by lazy {
        ComponentFactory.getApplicationComponent(this)
    }

    private lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator = applicationComponent.getNavigator()
        navigator.initialize(this, savedInstanceState)
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }
}
