package com.luanabarbosa.verity.githubapi.home.data.model

data class GithubUsersListModel(
    var username: String = "",
    var avatarImg: String = "",
    var htmlUrl: String = ""
) {
    companion object {
        val EMPTY = GithubUsersListModel()
    }
}
