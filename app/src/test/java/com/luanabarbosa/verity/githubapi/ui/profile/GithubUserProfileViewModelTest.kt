package com.luanabarbosa.verity.githubapi.ui.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GithubProfileRepoUseCase
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GithubUserProfileUseCase
import com.luanabarbosa.verity.githubapi.profile.ui.GithubUserProfileViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GithubUserProfileViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private val profileUseCase: GithubUserProfileUseCase = mockk()
    private val repoUseCase: GithubProfileRepoUseCase = mockk()
    private lateinit var viewModel: GithubUserProfileViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = GithubUserProfileViewModel(profileUseCase, repoUseCase)
    }

    @Test
    fun `test getUserProfile success`() = runBlocking {
        // Given
        val observerUsers = spyk<Observer<GithubUserProfileModel>>()
        val mockSuccess = spyk<GithubUserProfileModel>()

        coEvery { profileUseCase.invoke() } returns Result.success(mockSuccess)

        viewModel.profile.observeForever(observerUsers)

        // When
        viewModel.getUserProfile()

        // Then
        coVerify { profileUseCase() }
        verifyOrder {
            observerUsers.onChanged(mockSuccess)
        }
    }

    @Test
    fun `test getProfileRepo success`() = runBlocking {
        // Given
        val observerUsers = spyk<Observer<List<GithubProfileRepoListModel>>>()
        val mockSuccess = spyk<List<GithubProfileRepoListModel>>()

        coEvery { repoUseCase.invoke() } returns Result.success(mockSuccess)

        viewModel.repo.observeForever(observerUsers)

        // When
        viewModel.getProfileRepo()

        // Then
        coVerify { repoUseCase() }
        verifyOrder {
            observerUsers.onChanged(mockSuccess)
        }
    }

    @Test
    fun `test getUserProfile failure`() = runBlocking {
        // Given
        val observerError = spyk<Observer<Throwable>>()
        val mockError = RuntimeException("Test error")

        coEvery { profileUseCase() } returns Result.failure(mockError)

        viewModel.isError.observeForever(observerError)

        // When
        viewModel.getUserProfile()

        // Then
        coVerify { profileUseCase() }

        verifyOrder {
            observerError.onChanged(mockError)
        }
    }

    @Test
    fun `test getProfileRepo failure`() = runBlocking {
        // Given
        val observerError = spyk<Observer<Throwable>>()
        val mockError = RuntimeException("Test error")

        coEvery { repoUseCase() } returns Result.failure(mockError)

        viewModel.isError.observeForever(observerError)

        // When
        viewModel.getProfileRepo()

        // Then
        coVerify { repoUseCase() }
        verifyOrder {
            observerError.onChanged(mockError)
        }
    }

}
