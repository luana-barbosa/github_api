package com.luanabarbosa.verity.githubapi.profile.domain.usecase

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubUserProfileRepository

interface GithubUserProfileUseCase {
    suspend operator fun invoke(): Result<GithubUserProfileModel>
}

class GetUserProfile(private val repository : GithubUserProfileRepository) : GithubUserProfileUseCase {
    override suspend fun invoke(): Result<GithubUserProfileModel> =
        repository.getUserProfile()
}
