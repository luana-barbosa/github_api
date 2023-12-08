package com.luanabarbosa.verity.githubapi.profile.domain.usecase

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubProfileRepoRepository

interface GithubProfileRepoUseCase {
    suspend operator fun invoke(): Result<List<GithubProfileRepoListModel>>
}

class GetProfileRepo(private val repository : GithubProfileRepoRepository) : GithubProfileRepoUseCase {
    override suspend fun invoke(): Result<List<GithubProfileRepoListModel>> =
        repository.getProfileRepo()
}
