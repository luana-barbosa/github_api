package com.luanabarbosa.verity.githubapi.profile.data.mapper

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.data.response.GithubUserProfileResponse

interface GithubUserProfileMapper {
    fun toProfileModel(
        response: GithubUserProfileResponse,
    ): GithubUserProfileModel
}