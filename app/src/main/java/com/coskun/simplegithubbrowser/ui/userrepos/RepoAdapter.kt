package com.coskun.simplegithubbrowser.ui.userrepos

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coskun.simplegithubbrowser.R
import com.coskun.simplegithubbrowser.ui.common.model.RepoModel
import com.coskun.simplegithubbrowser.ui.common.model.RepoModelItemCallback
import com.coskun.simplegithubbrowser.util.inflate

class RepoAdapter(private val onRepoSelectListener: (RepoModel) -> Unit) :
    ListAdapter<RepoModel, RepoAdapter.RepoHolder>(RepoModelItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        return RepoHolder(parent.inflate(R.layout.item_repo_holder))
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewRepoName = itemView.findViewById<TextView>(R.id.textViewRepoName)
        private val imageViewFavoriteStatus =
            itemView.findViewById<ImageView>(R.id.imageViewFavoriteStatus)

        fun bind(model: RepoModel) = with(model) {

            textViewRepoName.text = name
            imageViewFavoriteStatus.setImageResource(
                if (isFavorite) R.drawable.ic_star_24dp else R.drawable.ic_star_border_24dp
            )

            itemView.setOnClickListener {
                onRepoSelectListener(model)
            }
        }
    }
}