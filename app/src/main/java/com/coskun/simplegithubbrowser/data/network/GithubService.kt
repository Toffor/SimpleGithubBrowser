package com.coskun.simplegithubbrowser.data.network

import com.coskun.simplegithubbrowser.data.entity.RepoEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user}/repos")
    suspend fun getUsersRepos(@Path("user") userName: String): List<RepoEntity>


}