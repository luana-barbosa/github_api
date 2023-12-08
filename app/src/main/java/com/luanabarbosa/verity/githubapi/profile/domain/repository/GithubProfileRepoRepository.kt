package com.luanabarbosa.verity.githubapi.profile.domain.repository

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel

interface GithubProfileRepoRepository {
    suspend fun getProfileRepo(): Result<List<GithubProfileRepoListModel>>
}