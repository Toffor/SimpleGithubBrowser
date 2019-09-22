package com.coskun.simplegithubbrowser.data.interactor

import com.coskun.simplegithubbrowser.data.entity.mapToRepoModel
import com.coskun.simplegithubbrowser.data.network.GithubService
import com.coskun.simplegithubbrowser.di.AppScope
import com.coskun.simplegithubbrowser.ui.common.model.RepoModel
import javax.inject.Inject

@AppScope
class RepoInteractor @Inject constructor(
    private val githubService: GithubService,
    private val localStorageInteractor: LocalStorageInteractor
) {

    // Memory cache for performance
    private var favoriteRepos = localStorageInteractor.getFavoriteRepoIds()

    suspend fun getUsersRepos(userName: String): List<RepoModel> {
        return githubService.getUsersRepos(userName).map { repoEntity ->
            repoEntity.mapToRepoModel(favoriteRepos)
        }
    }

    fun addToFavorites(repoId: Long) {
        favoriteRepos.add(repoId)
        localStorageInteractor.addToFavoriteRepos(repoId)
    }

    fun removeFromFavorites(repoId: Long) {
        favoriteRepos.remove(repoId)
        localStorageInteractor.removeFromFavoriteRepos(repoId)
    }


}