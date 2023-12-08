package com.luanabarbosa.verity.githubapi.profile.data.model

data class GithubProfileRepoListModel(
    var nameRepo: String = "",
    var fork: String = "",
    var description: String = "",
    var language: String = "",
    var stargazersCount: String = "",
    var forksCount: String = "",
) {
    companion object {
        val EMPTY = GithubProfileRepoListModel()
    }
}
