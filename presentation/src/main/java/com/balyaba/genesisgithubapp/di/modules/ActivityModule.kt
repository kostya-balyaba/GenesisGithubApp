package com.balyaba.genesisgithubapp.di.modules

import com.balyaba.genesisgithubapp.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}