package com.luanabarbosa.verity.githubapi.usecase.profile

import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubProfileRepoRepository
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GetProfileRepo
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetProfileRepoTest {

    // Mock repository
    private val repository: GithubProfileRepoRepository = mockk()

    private val getProfileRepo = GetProfileRepo(repository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `invoke() should return list of ProfileRepoModel on success`() = runBlocking {
        // Given
        val profileRepoList = listOf(mockk<GithubProfileRepoListModel>())
        coEvery { repository.getProfileRepo() } returns Result.success(profileRepoList)

        // When
        val result = getProfileRepo.invoke()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(profileRepoList, result.getOrNull())
    }

    @Test
    fun `invoke() should return an error on failure`() = runBlocking {
        // Given
        val expectedError = Exception("Repository error")
        coEvery { repository.getProfileRepo() } returns Result.failure(expectedError)

        // When
        val result = getProfileRepo.invoke()

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedError, result.exceptionOrNull())
    }
}
