package com.coskun.simplegithubbrowser.di

import androidx.lifecycle.ViewModel
import com.coskun.simplegithubbrowser.ui.userrepos.UserReposViewModule
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserReposViewModule::class)
    abstract fun bindUserReposViewModule(vm: UserReposViewModule): ViewModel

}


@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)