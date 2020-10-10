package com.balyaba.genesisgithubapp.features.repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.balyaba.domain.entities.Repository
import com.balyaba.genesisgithubapp.R


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class RepositoriesAdapter() : PagedListAdapter<Repository, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepositoryViewHolder).bind(getItem(position) as Repository)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                //DO
                return false
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                //DO
                return false
            }
        }
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repository: Repository) {

        }
    }
}