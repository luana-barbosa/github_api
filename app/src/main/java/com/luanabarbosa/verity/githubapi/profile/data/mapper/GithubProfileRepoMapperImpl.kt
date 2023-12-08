package com.luanabarbosa.verity.githubapi.profile.data.mapper

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.data.response.GithubProfileRepoResponse

class GithubProfileRepoMapperImpl : GithubProfileRepoMapper {
    override fun toRepoModel(response: GithubProfileRepoResponse) =
        GithubProfileRepoListModel(
            nameRepo = response.nameRepo.orEmpty(),
            fork = response.fork.orEmpty(),
            description = response.description.orEmpty(),
            language = response.language.orEmpty(),
            stargazersCount = response.stargazersCount.orEmpty(),
            forksCount = response.forksCount.orEmpty()
        )
}
