package com.luanabarbosa.verity.githubapi.profile.data.repository

import com.luanabarbosa.verity.githubapi.profile.data.mapper.GithubUserProfileMapper
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.data.service.ProfileApi
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubUserProfileRepository
import retrofit2.HttpException

class GithubProfileUserRepositoryImpl(
    private val service: ProfileApi,
    private val mapper: GithubUserProfileMapper
) : GithubUserProfileRepository {

    override suspend fun getUserProfile(): Result<GithubUserProfileModel> {
        return try {
            val request = service.getUserProfile()
            val response = mapper.toProfileModel(request)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(exception = e)
        }
    }
}
