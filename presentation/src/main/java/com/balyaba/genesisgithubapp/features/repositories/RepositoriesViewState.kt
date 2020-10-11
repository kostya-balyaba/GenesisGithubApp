package com.balyaba.genesisgithubapp.features.repositories


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
sealed class RepositoriesViewEffect

/*View Events - class for communication between View and viewModel*/
sealed class RepositoriesViewEvent {
    data class OnSearchRequest(val query: String) : RepositoriesViewEvent()
}