package com.coskun.simplegithubbrowser.ui.common.model

import androidx.recyclerview.widget.DiffUtil

data class RepoModel(
    val repoId: Long,
    val name: String,
    val ownerName: String,
    val ownerImageUrl: String?,
    val openIssueCount: Int,
    private var _starCount: Int,
    private var _isFavorite: Boolean
) {
    val isFavorite get() = _isFavorite

    val starCount get() = _starCount

    fun addToFavorites(): Int {
        _isFavorite = true
        return ++_starCount
    }

    fun removeFromFavorites(): Int {
        _isFavorite = false
        return --_starCount
    }

}

class RepoModelItemCallback : DiffUtil.ItemCallback<RepoModel>() {

    override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
        return oldItem.repoId == oldItem.repoId
    }

    override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
        return oldItem.isFavorite == newItem.isFavorite
    }

}