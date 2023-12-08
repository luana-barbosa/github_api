package com.luanabarbosa.verity.githubapi.home.data.mapper

import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.data.response.GithubUsersResponse

interface GithubHomeMapper {
    fun toCardModel(
        response: GithubUsersResponse,
    ): GithubUsersListModel
}