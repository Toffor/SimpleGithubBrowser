package com.coskun.simplegithubbrowser.data.entity

import com.coskun.simplegithubbrowser.ui.common.model.RepoModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoEntity(

    @Json(name = "id")
    val id: Long,

    @Json(name = "name")
    val name: String,

    @Json(name = "stargazers_count")
    val starCount: Int,

    @Json(name = "open_issues_count")
    val openIssueCount: Int
)

fun RepoEntity.mapToRepoModel(favoriteRepoIds: Set<Long>): RepoModel {
    return RepoModel(
        repoId = id,
        name = name,
        _isFavorite = favoriteRepoIds.contains(id),
        _starCount = starCount,
        openIssueCount = starCount
    )
}