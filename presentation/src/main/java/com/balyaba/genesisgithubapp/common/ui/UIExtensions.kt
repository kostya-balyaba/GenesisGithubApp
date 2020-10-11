package com.balyaba.genesisgithubapp.common.ui


import android.view.View
import androidx.appcompat.widget.SearchView

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun SearchView.onQueryTextChange(action: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String): Boolean {
            action.invoke(newText)
            return true
        }
    })
}