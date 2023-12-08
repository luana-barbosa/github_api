package com.luanabarbosa.verity.githubapi.profile.domain.repository

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel

interface GithubUserProfileRepository {
    suspend fun getUserProfile(): Result<GithubUserProfileModel>
}
