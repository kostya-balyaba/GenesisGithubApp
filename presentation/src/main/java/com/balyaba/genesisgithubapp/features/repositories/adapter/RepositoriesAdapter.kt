package com.balyaba.genesisgithubapp.features.repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.balyaba.domain.entities.Repository
import com.balyaba.genesisgithubapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_repository.view.*


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class RepositoriesAdapter : PagedListAdapter<Repository, RecyclerView.ViewHolder>(diffCallback) {

    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepositoryViewHolder).bind(getItem(position) as Repository)
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener?.onRepositoryClick(getItem(adapterPosition) as Repository)
            }
        }

        fun bind(repository: Repository) {
            with(itemView) {
                titleTextView.text = repository.name
                descriptionTextView.text = repository.description
                repoStarsTextView.text = repository.stars.toString()
                repoForksTextView.text = repository.forks.toString()

                repository.ownerAvatarUrl?.let {
                    Glide.with(itemView.context)
                        .load(it)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.circleCropTransform())
                        .into(photoView)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onRepositoryClick(repository: Repository)
    }
}