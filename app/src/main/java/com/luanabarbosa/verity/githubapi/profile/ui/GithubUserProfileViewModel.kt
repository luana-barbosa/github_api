package com.luanabarbosa.verity.githubapi.profile.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GithubProfileRepoUseCase
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GithubUserProfileUseCase
import kotlinx.coroutines.launch

class GithubUserProfileViewModel(
    private val userProfileUseCase: GithubUserProfileUseCase,
    private val userRepoUseCase:GithubProfileRepoUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _profile = MutableLiveData<GithubUserProfileModel>()
    val profile = _profile as LiveData<GithubUserProfileModel>

    private val _repo = MutableLiveData<List<GithubProfileRepoListModel>>()
    val repo = _repo as LiveData<List<GithubProfileRepoListModel>>

    private val _isError = MutableLiveData<Throwable>()
    val isError = _isError as LiveData<Throwable>

    fun getUserProfile() {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                userProfileUseCase().onSuccess {
                    _isLoading.value = false
                    _profile.postValue(it)
                }
                    .onFailure {
                        _isError.postValue(it)
                    }
            } catch (e: Throwable) {
                _isError.postValue(e)
            }
        }
    }

    fun getProfileRepo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                userRepoUseCase().onSuccess {
                    _isLoading.value = false
                    _repo.postValue(it)
                }
                    .onFailure {
                        _isError.postValue(it)
                    }
            } catch (e: Throwable) {
                _isError.postValue(e)
            }
        }
    }

    fun observerProfile(
        lifecycleOwner: LifecycleOwner,
        action: (GithubUserProfileModel) -> Unit,
    ) = _profile.observe(lifecycleOwner) {
        action(it)
    }

    fun observerRepos(
        lifecycleOwner: LifecycleOwner,
        action: (List<GithubProfileRepoListModel>) -> Unit,
    ) = _repo.observe(lifecycleOwner) {
        action(it)
    }
}