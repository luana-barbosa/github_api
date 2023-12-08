package com.luanabarbosa.verity.githubapi.home.data.repository

import com.luanabarbosa.verity.githubapi.home.data.mapper.GithubHomeMapper
import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.data.service.HomeApi
import com.luanabarbosa.verity.githubapi.home.domain.repository.GithubHomeRepository
import retrofit2.HttpException

class GithubHomeRepositoryImpl(
    private val service: HomeApi,
    private val mapper: GithubHomeMapper
) : GithubHomeRepository {

    override suspend fun getHomeUser(): Result<List<GithubUsersListModel>> {
        return try {
            val request = service.getUsers().map { mapper.toCardModel(it) }
            Result.success(request)
        } catch (e: HttpException) {
            Result.failure(exception = e)
        }
    }
}
