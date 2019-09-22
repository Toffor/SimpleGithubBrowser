package com.coskun.simplegithubbrowser.ui.repodetail

import android.os.Bundle
import coil.api.load
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.ui.common.BaseFragment
import com.coskun.simplegithubbrowser.ui.common.viewmodel.UserReposViewModel
import com.coskun.simplegithubbrowser.util.changeImageWithAnimation
import com.coskun.simplegithubbrowser.util.observe
import com.coskun.simplegithubbrowser.util.parentActivityViewModels
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class RepoDetailFragment : BaseFragment() {

    private val viewModel by parentActivityViewModels<UserReposViewModel>()

    override val layoutId = R.layout.fragment_repo_detail

    override fun onViewAppear(savedInstanceState: Bundle?) {

        imageViewBack.setOnClickListener {
            onBackPressed()
        }


        observe(viewModel.detailRepoModel) { repoModel ->
            imageViewUser.load(repoModel.ownerImageUrl)
            textViewRepoName.text = repoModel.name
            textViewOwnerName.text = repoModel.name
            setStarCount(repoModel.starCount)
            setIssueCount(repoModel.openIssueCount)
            textViewStarCount.text = getString(R.string.star_count, repoModel.starCount)
            imageViewFavoriteStatus.setImageResource(
                if (repoModel.isFavorite) R.drawable.ic_star_24dp else R.drawable.ic_star_border_24dp

            )
            imageViewFavoriteStatus.setOnClickListener {
                if (repoModel.isFavorite) {
                    imageViewFavoriteStatus.setImageResource(R.drawable.ic_star_border_24dp)
                    setStarCount(repoModel.removeFromFavorites())
                    viewModel.removeFromFavorites(repoModel.repoId)
                } else {
                    imageViewFavoriteStatus.changeImageWithAnimation(R.drawable.ic_star_24dp)
                    setStarCount(repoModel.addToFavorites())
                    viewModel.addToFavorites(repoModel.repoId)
                }
            }

        }

    }

    private fun setStarCount(starCount: Int) {
        textViewStarCount.text = getString(R.string.star_count, starCount)
    }

    private fun setIssueCount(issueCount: Int) {
        textViewOpenIssues.text = getString(R.string.open_issues, issueCount)
    }
}