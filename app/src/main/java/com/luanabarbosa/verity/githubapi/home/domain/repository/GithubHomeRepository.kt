package com.luanabarbosa.verity.githubapi.home.domain.repository

import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel

interface GithubHomeRepository {
    suspend fun getHomeUser(): Result<List<GithubUsersListModel>>
}