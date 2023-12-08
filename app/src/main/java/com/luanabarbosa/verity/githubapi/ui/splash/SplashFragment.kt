package com.luanabarbosa.verity.githubapi.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.verity.githubapi.R
import com.luanabarbosa.verity.githubapi.databinding.GithubSplashFragmentBinding
import com.luanabarbosa.verity.githubapi.ui.main.GithubMainActivity
import com.luanabarbosa.verity.toolkit.extensions.visible

class SplashFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())

    private val binding: GithubSplashFragmentBinding by lazy {
        GithubSplashFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideMainBottom()
        changeStatusBarColor()
        onAnimationStart()
    }

    private fun onAnimationStart() {
        with(binding) {
            animation.visible()
            animation.playAnimation()
            handler.postDelayed({
                view?.post {
                    onAnimationEnd()
                }
            }, ANIMATION_DELAY)
        }
    }

    private fun onAnimationEnd() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    private fun changeStatusBarColor() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.white
        )
    }

    private fun hideMainBottom(){
        (activity as? GithubMainActivity)?.hideBottomNavBar()
    }

    companion object {
        private const val ANIMATION_DELAY = 3000L
    }

}
