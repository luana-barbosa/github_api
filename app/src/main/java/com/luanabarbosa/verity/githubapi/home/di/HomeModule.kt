package com.luanabarbosa.verity.githubapi.home.di

import com.luanabarbosa.verity.githubapi.home.data.mapper.GithubHomeMapper
import com.luanabarbosa.verity.githubapi.home.data.mapper.GithubHomeMapperImpl
import com.luanabarbosa.verity.githubapi.home.data.repository.GithubHomeRepositoryImpl
import com.luanabarbosa.verity.githubapi.home.data.service.HomeApi
import com.luanabarbosa.verity.githubapi.home.domain.repository.GithubHomeRepository
import com.luanabarbosa.verity.githubapi.home.domain.usecase.GetHomeUsers
import com.luanabarbosa.verity.githubapi.home.domain.usecase.GithubUsersUseCase
import com.luanabarbosa.verity.githubapi.home.ui.GithubHomeViewModel
import com.luanabarbosa.verity.remote.retrofit.NetworkInitialization
import org.koin.dsl.module

val homeModule = module {
    single { NetworkInitialization().createService(HomeApi::class.java) }
    single<GithubHomeRepository> { GithubHomeRepositoryImpl(get(), get()) }
    factory<GithubHomeMapper> { GithubHomeMapperImpl() }
    single<GithubUsersUseCase> { GetHomeUsers(get()) }
    single { GithubHomeViewModel(get()) }
}
