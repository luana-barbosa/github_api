package com.luanabarbosa.verity.githubapi.profile.data.model

data class GithubUserProfileModel(
    var username: String = "",
    var avatarImg: String = "",
    var htmlUrl: String = "",
    var followers: String = "",
    var following: String = "",
    var realName: String = "",
    var company: String = "",
    var location: String = "",
    var publicRepos: String = ""
) {
    companion object {
        val EMPTY = GithubUserProfileModel()
    }
}
