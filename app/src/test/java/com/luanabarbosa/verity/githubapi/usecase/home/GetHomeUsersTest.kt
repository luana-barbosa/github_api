package com.luanabarbosa.verity.githubapi.usecase.home

import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.domain.repository.GithubHomeRepository
import com.luanabarbosa.verity.githubapi.home.domain.usecase.GetHomeUsers
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
class GetHomeUsersTest {

    // Mock repository
    private val repository: GithubHomeRepository = mockk()

    private val getHomeUsers = GetHomeUsers(repository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `invoke() should return list of GithubUsersListModel on success`() = runBlocking {
        // Given
        val githubUsersList = listOf(mockk<GithubUsersListModel>())
        coEvery { repository.getHomeUser() } returns Result.success(githubUsersList)

        // When
        val result = getHomeUsers.invoke()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(githubUsersList, result.getOrNull())
    }

    @Test
    fun `invoke() should return an error on failure`() = runBlocking {
        // Given
        val error = RuntimeException("Test error")
        coEvery { repository.getHomeUser() } returns Result.failure(error)

        // When
        val result = getHomeUsers.invoke()

        // Then
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }
}
