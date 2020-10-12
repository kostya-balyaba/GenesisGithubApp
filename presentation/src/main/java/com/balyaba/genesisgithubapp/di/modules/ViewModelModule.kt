package com.balyaba.genesisgithubapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balyaba.genesisgithubapp.common.vm.ViewModelFactory
import com.balyaba.genesisgithubapp.common.vm.VmKey
import com.balyaba.genesisgithubapp.features.favorites.FavoritesViewModel
import com.balyaba.genesisgithubapp.features.repositories.RepositoriesViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @VmKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @VmKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel
}