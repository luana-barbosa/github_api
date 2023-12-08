package com.luanabarbosa.verity.githubapi.profile.data.response

import com.google.gson.annotations.SerializedName

data class GithubProfileRepoResponse(
    @SerializedName("name")
    var nameRepo: String? = null,
    @SerializedName("fork")
    var fork: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("language")
    var language: String? = null,
    @SerializedName("stargazers_count")
    var stargazersCount: String? = null,
    @SerializedName("forks_count")
    var forksCount: String? = null
)
