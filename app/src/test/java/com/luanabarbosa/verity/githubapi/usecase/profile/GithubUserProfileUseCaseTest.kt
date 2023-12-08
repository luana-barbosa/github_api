package com.luanabarbosa.verity.githubapi.usecase.profile

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubUserProfileRepository
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GetUserProfile
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserProfileTest {

    @Test
    fun `invoke should return UserProfile from repository`() = runBlocking {
        // Given
        val expectedUserProfile = GithubUserProfileModel(
            username = "torvalds",
            avatarImg = "https://avatars.githubusercontent.com/u/1024025?v=4",
            htmlUrl = "https://api.github.com/users/torvalds",
            followers = "195550",
            following = "0",
            realName = "Linus Torvalds",
            company = "Linux Foundation",
            location = "Portland, OR",
            publicRepos = "7"
        )
        val repositoryMock = mockk<GithubUserProfileRepository>()
        coEvery { repositoryMock.getUserProfile() } returns Result.success(expectedUserProfile)
        val getUserProfile = GetUserProfile(repositoryMock)

        // When
        val result = getUserProfile.invoke()

        // Then
        assertEquals(Result.success(expectedUserProfile), result)
    }

    @Test
    fun `invoke should return an error if repository fails`() = runBlocking {
        // Given
        val expectedError = Exception("Repository error")
        val repositoryMock = mockk<GithubUserProfileRepository>()
        coEvery { repositoryMock.getUserProfile() } returns Result.failure(expectedError)
        val getUserProfile = GetUserProfile(repositoryMock)

        // When
        val result = getUserProfile.invoke()

        // Then
        assertEquals(Result.failure<GithubUserProfileModel>(expectedError), result)
    }
}

