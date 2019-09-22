package com.coskun.simplegithubbrowser.ui.userrepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.data.interactor.RepoInteractor
import com.coskun.simplegithubbrowser.ui.common.BaseViewModel
import com.coskun.simplegithubbrowser.ui.common.model.RepoModel
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject


class UserReposViewModule @Inject constructor(
    private val repoInteractor: RepoInteractor
) : BaseViewModel() {

    private val _usersReposList = MutableLiveData<List<RepoModel>>()
    var usersReposList: LiveData<List<RepoModel>> = _usersReposList

    fun searchForUser(userName: String) = cancelPreviousAndLaunch(onError = ::handleSearchError) {
        updateLoadingStatus(true)
        _usersReposList.value = emptyList()
        _usersReposList.value = repoInteractor.getUsersRepos(userName)
        updateLoadingStatus(false)
    }

    private fun handleSearchError(throwable: Throwable) {
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

    companion object {
        const val CODE_USER_NOT_FOUND = 101
        const val CODE_NO_CONNECTION = 102
    }


}