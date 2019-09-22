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

    @Json(name = "owner")
    val owner: OwnerEntity,

    @Json(name = "stargazers_count")
    val starCount: Int,

    @Json(name = "open_issues_count")
    val openIssueCount: Int


)

fun RepoEntity.mapToRepoModel(favoriteRepoIds: Set<Long>): RepoModel {
    val isFavorite = favoriteRepoIds.contains(id)
    return RepoModel(
        repoId = id,
        name = name,
        ownerName = owner.ownerName,
        ownerImageUrl = owner.ownerImage,
        _isFavorite = isFavorite,
        _starCount = if (isFavorite) starCount + 1 else starCount,
        openIssueCount = starCount
    )
}