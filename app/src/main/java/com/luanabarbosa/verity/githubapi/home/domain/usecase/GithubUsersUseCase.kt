package com.luanabarbosa.verity.githubapi.home.domain.usecase

import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.domain.repository.GithubHomeRepository

interface GithubUsersUseCase {
    suspend operator fun invoke(): Result<List<GithubUsersListModel>>
}

class GetHomeUsers(private val repository : GithubHomeRepository) : GithubUsersUseCase {
    override suspend fun invoke(): Result<List<GithubUsersListModel>> =
        repository.getHomeUser()
}
