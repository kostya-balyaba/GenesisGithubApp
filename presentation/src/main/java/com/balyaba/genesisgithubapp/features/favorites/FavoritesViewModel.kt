package com.balyaba.genesisgithubapp.features.favorites

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.usecases.GetFavoritesUseCase
import com.balyaba.domain.usecases.RemoveFromFavoritesUseCase
import com.balyaba.genesisgithubapp.common.vm.BaseViewModel
import com.balyaba.genesisgithubapp.features.favorites.adapter.FavoritesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : BaseViewModel<FavoritesViewState, FavoritesViewEffect, FavoritesViewEvent>() {

    init {
        viewState = FavoritesViewState(Status.Loading, emptyList())
    }

    override fun processEvent(viewEvent: FavoritesViewEvent) {
        when (viewEvent) {
            is FavoritesViewEvent.OnLoadFavoritesEvent -> loadFavoritesRepositories()
        }
    }

    private fun loadFavoritesRepositories() {
        viewState = viewState.copy(status = Status.Loading)
        viewModelScope.launch {
            viewState = try {
                val repositories =
                    withContext(Dispatchers.IO) { getFavoritesUseCase.getFavorites() }
                if (repositories.isEmpty())
                    viewState.copy(status = Status.Empty, repositories = repositories)
                else
                    viewState.copy(status = Status.Success, repositories = repositories)
            } catch (e: Exception) {
                viewState.copy(status = Status.Error)
            }
        }
    }

    val adapterListener = object : FavoritesAdapter.OnItemClickListener {
        override fun onRepositoryClick(repository: Repository) {
            repository.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                viewEffect = FavoritesViewEffect.OnOpenLink(intent)
            }
        }

        override fun onFavoriteClick(repository: Repository, position: Int) {
            removeFromFavorites(repository)
            viewEffect = FavoritesViewEffect.RemoveRepositoryByPosition(position)
        }
    }

    private fun removeFromFavorites(repository: Repository) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromFavoritesUseCase.remove(repository)
        }
    }
}