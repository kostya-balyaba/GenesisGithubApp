package com.balyaba.genesisgithubapp.di

import android.content.Context
import com.balyaba.data.di.*
import com.balyaba.genesisgithubapp.App
import com.balyaba.genesisgithubapp.di.modules.ActivityModule
import com.balyaba.genesisgithubapp.di.modules.FragmentModule
import com.balyaba.genesisgithubapp.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ApiModule::class,
        DataSourceModule::class,
        RepositoriesModule::class,
        MappersModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}