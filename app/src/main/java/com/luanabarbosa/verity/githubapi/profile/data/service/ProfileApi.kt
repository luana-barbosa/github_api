package com.luanabarbosa.verity.githubapi.profile.data.service

import com.luanabarbosa.verity.githubapi.profile.data.response.GithubProfileRepoResponse
import com.luanabarbosa.verity.githubapi.profile.data.response.GithubUserProfileResponse
import com.luanabarbosa.verity.remote.config.ConfigApi.REPO_TORVALDS_API_URL
import com.luanabarbosa.verity.remote.config.ConfigApi.USERS_TORVALDS_API_URL
import retrofit2.http.GET

interface ProfileApi {
    @GET(USERS_TORVALDS_API_URL)
    suspend fun getUserProfile(): GithubUserProfileResponse

    @GET(REPO_TORVALDS_API_URL)
    suspend fun getProfileRepo(): List<GithubProfileRepoResponse>
}
