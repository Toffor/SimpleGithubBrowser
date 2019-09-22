package com.coskun.simplegithubbrowser.ui.common.model

import androidx.recyclerview.widget.DiffUtil

data class RepoModel(
    val repoId: Long,
    val name: String,
    val openIssueCount: Int,
    var _starCount: Int,
    var _isFavorite: Boolean
) {
    val isFavorite get() = _isFavorite

    val starCount get() = _starCount

    fun favRepo(): Int {
        _isFavorite = true
        return ++_starCount
    }

    fun unFavRepo(): Int {
        _isFavorite = true
        return --_starCount
    }

}

class RepoModelItemCallback : DiffUtil.ItemCallback<RepoModel>() {

    override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
        return oldItem.repoId == oldItem.repoId
    }

    override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
        return oldItem._isFavorite == newItem._isFavorite
    }

}