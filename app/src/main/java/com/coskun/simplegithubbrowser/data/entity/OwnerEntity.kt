package com.coskun.simplegithubbrowser.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerEntity(

    @Json(name = "login")
    val ownerName: String,

    @Json(name = "avatar_url")
    val ownerImage: String?
)