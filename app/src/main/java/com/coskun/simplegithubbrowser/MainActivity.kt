package com.coskun.simplegithubbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coskun.simplegithubbrowser.di.ComponentFactory
import com.coskun.simplegithubbrowser.ui.navigator.Navigator

class MainActivity : AppCompatActivity() {

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = ComponentFactory.getApplicationComponent(this).getNavigator()
        navigator.initialize(this, savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }
}
