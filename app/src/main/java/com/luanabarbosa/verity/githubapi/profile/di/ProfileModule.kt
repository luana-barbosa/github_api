package com.luanabarbosa.verity.githubapi.profile.di

import com.luanabarbosa.verity.githubapi.profile.data.mapper.GithubProfileRepoMapper
import com.luanabarbosa.verity.githubapi.profile.data.mapper.GithubProfileRepoMapperImpl
import com.luanabarbosa.verity.githubapi.profile.data.mapper.GithubUserProfileMapper
import com.luanabarbosa.verity.githubapi.profile.data.mapper.GithubUserProfileMapperImpl
import com.luanabarbosa.verity.githubapi.profile.data.repository.GithubProfileRepoRepositoryImpl
import com.luanabarbosa.verity.githubapi.profile.data.repository.GithubProfileUserRepositoryImpl
import com.luanabarbosa.verity.githubapi.profile.data.service.ProfileApi
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubProfileRepoRepository
import com.luanabarbosa.verity.githubapi.profile.domain.repository.GithubUserProfileRepository
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GetProfileRepo
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GetUserProfile
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GithubProfileRepoUseCase
import com.luanabarbosa.verity.githubapi.profile.domain.usecase.GithubUserProfileUseCase
import com.luanabarbosa.verity.githubapi.profile.ui.GithubUserProfileViewModel
import com.luanabarbosa.verity.remote.retrofit.NetworkInitialization
import org.koin.dsl.module

val profileModule = module {
    single { NetworkInitialization().createService(ProfileApi::class.java) }
    single<GithubUserProfileRepository> { GithubProfileUserRepositoryImpl(get(), get()) }
    single<GithubProfileRepoRepository> { GithubProfileRepoRepositoryImpl(get(), get()) }
    factory<GithubProfileRepoMapper> { GithubProfileRepoMapperImpl() }
    factory<GithubUserProfileMapper> { GithubUserProfileMapperImpl() }
    single<GithubProfileRepoUseCase> { GetProfileRepo(get()) }
    single<GithubUserProfileUseCase> { GetUserProfile(get()) }
    single { GithubUserProfileViewModel(get(), get()) }
}
