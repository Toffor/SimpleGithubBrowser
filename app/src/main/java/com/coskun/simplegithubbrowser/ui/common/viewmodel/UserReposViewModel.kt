package com.coskun.simplegithubbrowser.ui.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.data.interactor.RepoInteractor
import com.coskun.simplegithubbrowser.ui.common.BaseViewModel
import com.coskun.simplegithubbrowser.ui.common.model.RepoModel
import com.coskun.simplegithubbrowser.ui.navigator.Navigator
import com.coskun.simplegithubbrowser.util.logError
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject


class UserReposViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repoInteractor: RepoInteractor
) : BaseViewModel() {

    private val _usersReposList = MutableLiveData<List<RepoModel>>()
    var usersReposList: LiveData<List<RepoModel>> = _usersReposList

    private val _detailRepoModel = MutableLiveData<RepoModel>()
    val detailRepoModel: LiveData<RepoModel> = _detailRepoModel

    fun searchForUser(userName: String) = cancelPreviousAndLaunch(onError = ::handleSearchError) {
        updateLoadingStatus(true)
        _usersReposList.value = emptyList()
        _usersReposList.value = repoInteractor.getUsersRepos(userName)
        updateLoadingStatus(false)
    }

    private fun handleSearchError(throwable: Throwable) {
        logError(throwable)
        updateLoadingStatus(false)
        when (throwable) {
            is HttpException -> {
                if (throwable.code() == 404) {
                    updateErrorStatus(
                        R.string.error_user_not_found,
                        R.string.search_again,
                        CODE_USER_NOT_FOUND
                    )
                } else {
                    updateErrorStatus(
                        R.string.error_no_connection,
                        R.string.try_again,
                        CODE_NO_CONNECTION
                    )
                }
            }

            is UnknownHostException -> {
                updateErrorStatus(
                    R.string.error_no_connection,
                    R.string.try_again,
                    CODE_NO_CONNECTION
                )
            }
        }
    }

    fun addToFavorites(repoId: Long) {
        repoInteractor.addToFavorites(repoId)
    }

    fun removeFromFavorites(repoId: Long) {
        repoInteractor.removeFromFavorites(repoId)
    }



    fun navigateToRepoDetails(repoModel: RepoModel) {
        _detailRepoModel.value = repoModel
        navigator.navigateToRepoDetails()
    }

    companion object {
        const val CODE_USER_NOT_FOUND = 101
        const val CODE_NO_CONNECTION = 102
    }


}