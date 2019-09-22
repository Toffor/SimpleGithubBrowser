package com.coskun.simplegithubbrowser.ui.userrepos

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import androidx.core.widget.doAfterTextChanged
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.ui.common.BaseFragment
import com.coskun.simplegithubbrowser.util.*
import kotlinx.android.synthetic.main.fragment_user_repos.*

class UserReposFragment : BaseFragment() {

    private val viewModel by parentActivityViewModels<UserReposViewModule>()

    private val repoAdapter = RepoAdapter()

    override val layoutId = R.layout.fragment_user_repos

    override fun onViewAppear(savedInstanceState: Bundle?) {

        initViews()

        bindObservers()
    }

    private fun initViews() {

        recyclerViewUserRepos.adapter = repoAdapter

        updateSubmitButton(editTextSearch.editableText)

        editTextSearch.doAfterTextChanged(::updateSubmitButton)

        editTextSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == KeyEvent.ACTION_DOWN) {
                return@setOnEditorActionListener !searchForUser()
            }
            false
        }

        buttonSubmit.setOnClickListener { view ->
            editTextSearch.clearFocus()
            view.hideSoftKeyboard()
            searchForUser()
        }
    }

    private fun bindObservers() = with(viewModel) {

        observe(usersReposList, repoAdapter::submitList)

        observe(loadingStatus) { (isLoading, _) ->
            if (isLoading) progressBarLoading.show() else progressBarLoading.hide()
        }

        observe(errorStatus) { errorStatus ->
            when (errorStatus.errorCode) {
                UserReposViewModule.CODE_USER_NOT_FOUND -> showSnackbar(errorStatus) {
                    editTextSearch.requestFocus()
                    editTextSearch.showSoftKeyboard()
                }

                UserReposViewModule.CODE_NO_CONNECTION -> showSnackbar(errorStatus) {
                    searchForUser()
                }
            }
        }
    }

    private fun searchForUser(): Boolean {
        editTextSearch.text?.toString()?.takeIf { it.length > 2 }?.let(viewModel::searchForUser)
            ?: return false
        return true
    }


    private fun updateSubmitButton(editable: Editable?) {
        val isEnabled = (editable != null && editable.length > 2)
        buttonSubmit.apply {
            this.isEnabled = isEnabled
            //this.alpha = if (isEnabled) 1f else 0.8f
        }
    }
}