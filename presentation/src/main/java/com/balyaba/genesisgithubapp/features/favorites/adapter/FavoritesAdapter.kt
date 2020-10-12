package com.balyaba.genesisgithubapp.features.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balyaba.domain.entities.Repository
import com.balyaba.genesisgithubapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_repository.view.*


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

class FavoritesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val repositories: MutableList<Repository> = mutableListOf()
    var listener: OnItemClickListener? = null

    fun replaceItems(newItemsList: List<Repository>) {
        repositories.clear()
        repositories.addAll(newItemsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepositoryViewHolder).bind(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    fun removeItemByPosition(position: Int) {
        repositories.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener?.onRepositoryClick(repositories[adapterPosition])
            }
            with(itemView) {
                isFavoriteIconView.setOnClickListener {
                    listener?.onFavoriteClick(
                        repositories[adapterPosition],
                        adapterPosition
                    )
                }
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

                bindFavoriteState(repository)
            }
        }

        private fun bindFavoriteState(repository: Repository) {
            with(itemView) {
                isFavoriteIconView.setImageResource(
                    if (repository.isFavorite) R.drawable.ic_favorite
                    else R.drawable.ic_not_favorite
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onRepositoryClick(repository: Repository)
        fun onFavoriteClick(repository: Repository, position: Int)
    }
}