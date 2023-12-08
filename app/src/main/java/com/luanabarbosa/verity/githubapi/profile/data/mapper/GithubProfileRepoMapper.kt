package com.luanabarbosa.verity.githubapi.profile.data.mapper

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.data.response.GithubProfileRepoResponse

interface GithubProfileRepoMapper {
    fun toRepoModel(
        response: GithubProfileRepoResponse,
    ): GithubProfileRepoListModel
}