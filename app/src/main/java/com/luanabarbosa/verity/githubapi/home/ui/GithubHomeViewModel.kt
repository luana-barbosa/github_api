package com.luanabarbosa.verity.githubapi.home.ui

import  androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.domain.usecase.GithubUsersUseCase
import kotlinx.coroutines.launch

class GithubHomeViewModel(
    private val usersUseCase: GithubUsersUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _user = MutableLiveData<List<GithubUsersListModel>>()
    val user = _user as LiveData<List<GithubUsersListModel>>

    private val _isError = MutableLiveData<Throwable>()
    val isError = _isError as LiveData<Throwable>

    fun getHomeUsers() {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                usersUseCase().onSuccess {
                    _isLoading.value = false
                    _user.postValue(it)
                }
                    .onFailure {
                        _isError.postValue(it)
                    }
            } catch (e: Throwable) {
                _isError.postValue(e)
            }
        }
    }

    fun observerCards(
        lifecycleOwner: LifecycleOwner,
        action: (List<GithubUsersListModel>) -> Unit,
    ) = _user.observe(lifecycleOwner) {
        action(it)
    }
}
