package com.luanabarbosa.verity.githubapi.profile.data.mapper

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.data.response.GithubUserProfileResponse

class GithubUserProfileMapperImpl : GithubUserProfileMapper {
    override fun toProfileModel(response: GithubUserProfileResponse) =
        GithubUserProfileModel(
            username = response.username.orEmpty(),
            avatarImg = response.avatarImg.orEmpty(),
            htmlUrl = response.htmlUrl.orEmpty(),
            followers = response.followers.orEmpty(),
            following = response.following.orEmpty(),
            realName = response.realName.orEmpty(),
            company = response.company.orEmpty(),
            location = response.location.orEmpty(),
            publicRepos = response.publicRepos.orEmpty()
        )
}
