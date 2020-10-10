package com.balyaba.genesisgithubapp

import android.app.Application
import com.balyaba.genesisgithubapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()

        appComponent.inject(this)
    }
}