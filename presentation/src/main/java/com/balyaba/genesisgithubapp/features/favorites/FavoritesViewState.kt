package com.balyaba.genesisgithubapp.features.favorites

import android.content.Intent
import com.balyaba.domain.entities.Repository


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

data class FavoritesViewState(val status: Status, val repositories: List<Repository>)

sealed class Status {
    object Success : Status()
    object Loading : Status()
    object Error : Status()
    object Empty : Status()
}

/*View Effects - class for communication between viewModel and View*/
sealed class FavoritesViewEffect {
    data class OnOpenLink(val intent: Intent) : FavoritesViewEffect()
    data class RemoveRepositoryByPosition(val position: Int) : FavoritesViewEffect()
}

/*View Events - class for communication between View and viewModel*/
sealed class FavoritesViewEvent {
    object OnLoadFavoritesEvent : FavoritesViewEvent()
}