package com.coskun.simplegithubbrowser.data.entity

import com.coskun.simplegithubbrowser.ui.common.model.RepoModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoEntity(

    @Json(name = "id")
    val id: Long
)

fun RepoEntity.mapToRepoModel(favoriteRepoIds: Set<Long>): RepoModel{
    return RepoModel(id, favoriteRepoIds.contains(id))
}