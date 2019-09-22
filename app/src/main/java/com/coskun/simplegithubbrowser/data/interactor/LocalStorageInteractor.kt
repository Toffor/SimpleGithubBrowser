package com.coskun.simplegithubbrowser.data.interactor

import com.coskun.simplegithubbrowser.data.database.SimpleDatabase
import com.coskun.simplegithubbrowser.di.AppScope
import javax.inject.Inject

const val KEY_FAVORITE_REPOS = "users_favorite_repos"

@AppScope
class LocalStorageInteractor @Inject constructor(
    private val simpleDatabase: SimpleDatabase
) {


    fun addToFavoriteRepos(repoId: Long) = editAndSave {
        add(repoId)
    }

    fun removeFromFavoriteRepos(repoId: Long) = editAndSave {
        remove(repoId)
    }

    fun getFavoriteRepoIds(): MutableSet<Long> {
        return simpleDatabase.getLongSet(KEY_FAVORITE_REPOS).toMutableSet()
    }

    private fun editAndSave(action: MutableSet<Long>.() -> Unit) {
        val favoriteRepos = simpleDatabase.getLongSet(KEY_FAVORITE_REPOS).toMutableSet()
        favoriteRepos.action()
        simpleDatabase.putLongSet(KEY_FAVORITE_REPOS, favoriteRepos)
    }
}