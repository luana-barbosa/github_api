package com.luanabarbosa.verity.githubapi.home.data.service

import com.luanabarbosa.verity.githubapi.home.data.response.GithubUsersResponse
import com.luanabarbosa.verity.remote.config.ConfigApi.USERS_API_URL
import retrofit2.http.GET

interface HomeApi {
    @GET(USERS_API_URL)
    suspend fun getUsers(): List<GithubUsersResponse>
}
