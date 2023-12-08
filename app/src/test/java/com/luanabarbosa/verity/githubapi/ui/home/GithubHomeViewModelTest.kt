package com.luanabarbosa.verity.githubapi.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.domain.usecase.GithubUsersUseCase
import com.luanabarbosa.verity.githubapi.home.ui.GithubHomeViewModel
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
class GithubHomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private val usersUseCase: GithubUsersUseCase = mockk()
    private lateinit var viewModel: GithubHomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = GithubHomeViewModel(usersUseCase)
    }

    @Test
    fun `test getHomeUsers success`() = runBlocking {
        // Given
        val observerUsers = spyk<Observer<List<GithubUsersListModel>>>()
        val mockSuccess = spyk<List<GithubUsersListModel>>()

        coEvery { usersUseCase.invoke() } returns Result.success(mockSuccess)

        viewModel.user.observeForever(observerUsers)

        // When
        viewModel.getHomeUsers()

        // Then
        coVerify { usersUseCase() }
        verifyOrder {
            observerUsers.onChanged(mockSuccess)
        }
    }

    @Test
    fun `test getHomeUsers failure`() = runBlocking {
        // Given
        val observerError = spyk<Observer<Throwable>>()
        val mockError = RuntimeException("Test error")

        coEvery { usersUseCase() } returns Result.failure(mockError)

        viewModel.isError.observeForever(observerError)

        // When
        viewModel.getHomeUsers()

        // Then
        coVerify { usersUseCase() }
        verifyOrder {
            observerError.onChanged(mockError)
        }
    }
}
