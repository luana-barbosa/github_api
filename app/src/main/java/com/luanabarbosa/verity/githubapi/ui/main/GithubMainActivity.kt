package com.luanabarbosa.verity.githubapi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.luanabarbosa.verity.githubapi.R
import com.luanabarbosa.verity.githubapi.databinding.NavActivityBinding
import com.luanabarbosa.verity.toolkit.extensions.gone
import com.luanabarbosa.verity.toolkit.extensions.visible

class GithubMainActivity : AppCompatActivity() {

    private val binding: NavActivityBinding by lazy {
        NavActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavBar()
    }

    private fun setupBottomNavBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

     fun hideBottomNavBar(){
        binding.bottomNavigationView.gone()
    }

    fun showBottomNavBar(){
        binding.bottomNavigationView.visible()
    }

}
