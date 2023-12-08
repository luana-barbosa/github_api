package com.luanabarbosa.verity.githubapi.home.data.response

import com.google.gson.annotations.SerializedName

data class GithubUsersResponse(
    @SerializedName("login")
    var username: String? = null,
    @SerializedName("avatar_url")
    var avatarImg: String? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    @SerializedName("followers_url")
    var followers: String? = null,
    @SerializedName("repos_url")
    var repos: String? = null,
    @SerializedName("following_url")
    var following: String? = null
)
