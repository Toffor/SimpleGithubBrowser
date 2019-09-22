package com.coskun.simplegithubbrowser.di

import android.content.Context
import android.content.SharedPreferences
import com.coskun.simplegithubbrowser.GithubApp
import com.coskun.simplegithubbrowser.data.network.NetworkModule
import dagger.Module
import dagger.Provides

private const val PREF_NAME = "github_app"

@Module(includes = [ViewModelModule::class, NetworkModule::class])
object ApplicationModule {

    @Provides
    @AppScope
    @JvmStatic
    fun providesSharedPreferences(context: GithubApp): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


}