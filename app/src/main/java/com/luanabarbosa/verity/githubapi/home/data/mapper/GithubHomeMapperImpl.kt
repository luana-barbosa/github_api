package com.luanabarbosa.verity.githubapi.home.data.mapper

import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.data.response.GithubUsersResponse

class GithubHomeMapperImpl : GithubHomeMapper {
    override fun toCardModel(response: GithubUsersResponse) =
        GithubUsersListModel(
            username = response.username.orEmpty(),
            avatarImg = response.avatarImg.orEmpty(),
            htmlUrl = response.htmlUrl.orEmpty()
        )
}
