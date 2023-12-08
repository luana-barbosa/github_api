package com.luanabarbosa.verity.githubapi.ui

import android.app.Application
import com.luanabarbosa.verity.githubapi.home.di.homeModule
import com.luanabarbosa.verity.githubapi.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GithubApp)
            modules(listOf(homeModule, profileModule))
        }
    }
}
