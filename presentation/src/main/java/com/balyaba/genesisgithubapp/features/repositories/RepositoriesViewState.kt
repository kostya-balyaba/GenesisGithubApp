package com.balyaba.genesisgithubapp.features.repositories

import android.content.Intent


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

data class RepositoriesViewState(val status: Status)

sealed class Status {
    object Success : Status()
    object Loading : Status()
    object Error : Status()
    object Empty : Status()
}

/*View Effects - class for communication between viewModel and View*/
sealed class RepositoriesViewEffect {
    data class OnOpenLink(val intent: Intent) : RepositoriesViewEffect()
    data class UpdateRepositoryByPosition(val position: Int) : RepositoriesViewEffect()
}

/*View Events - class for communication between View and viewModel*/
sealed class RepositoriesViewEvent {
    data class OnSearchRequestEvent(val query: String) : RepositoriesViewEvent()
}