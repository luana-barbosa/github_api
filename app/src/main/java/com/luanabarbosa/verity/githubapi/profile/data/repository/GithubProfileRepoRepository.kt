package com.luanabarbosa.verity.githubapi.profile.data.repository

import com.luanabarbosa.verity.githubapi.profile.data.mapper.GithubProfileRepoMapper
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.data.service.ProfileApi
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubProfileRepoRepository
import retrofit2.HttpException

class GithubProfileRepoRepositoryImpl(
    private val service: ProfileApi,
    private val mapper: GithubProfileRepoMapper
) : GithubProfileRepoRepository {

    override suspend fun getProfileRepo(): Result<List<GithubProfileRepoListModel>> {
        return try {
            val request = service.getProfileRepo().map { mapper.toRepoModel(it) }
            Result.success(request)
        } catch (e: HttpException) {
            Result.failure(exception = e)
        }
    }
}
