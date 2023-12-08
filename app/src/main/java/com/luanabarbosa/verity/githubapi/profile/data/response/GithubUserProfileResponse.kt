package com.luanabarbosa.verity.githubapi.profile.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserProfileResponse(
    @SerializedName("login")
    var username: String? = null,
    @SerializedName("avatar_url")
    var avatarImg: String? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    @SerializedName("followers")
    var followers: String? = null,
    @SerializedName("following")
    var following: String? = null,
    @SerializedName("repos_url")
    var repos: String? = null,
    @SerializedName("name")
    var realName: String? = null,
    @SerializedName("company")
    var company: String? = null,
    @SerializedName("location")
    var location: String? = null,
    @SerializedName("public_repos")
    var publicRepos: String? = null
)
