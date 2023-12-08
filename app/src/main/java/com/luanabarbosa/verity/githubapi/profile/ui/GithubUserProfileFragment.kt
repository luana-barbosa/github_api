package com.luanabarbosa.verity.githubapi.profile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.verity.githubapi.R
import com.luanabarbosa.verity.githubapi.databinding.GithubProfileUserFragmentBinding
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubUserProfileModel
import com.luanabarbosa.verity.githubapi.ui.main.GithubMainActivity
import com.luanabarbosa.verity.toolkit.extensions.gone
import com.luanabarbosa.verity.toolkit.extensions.load
import com.luanabarbosa.verity.toolkit.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubUserProfileFragment : Fragment() {
    private val profileViewModel: GithubUserProfileViewModel by viewModel()
    private val binding: GithubProfileUserFragmentBinding by lazy {
        GithubProfileUserFragmentBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? GithubMainActivity)?.showBottomNavBar()
        setupObserver()
        changeStatusBarColor()
        profileViewModel.getUserProfile()
        setupOnLoading()
    }

    private fun setupObserver() {
        profileViewModel.observerProfile(this) {
            setupInfoProfile(it)
        }

        profileViewModel.isError.observe(viewLifecycleOwner) {
            setupOnError()
        }
    }

    private fun setupOnLoading() {
        with(binding) {
            containerSuccess.gone()
            loading.visible()
        }
    }

    private fun setupOnError() {
        with(binding) {
            containerSuccess.gone()
            errorContainer.visible()
            errorContainer.setOnClickListener {
                profileViewModel.getUserProfile()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupInfoProfile(user: GithubUserProfileModel) {
        with(binding) {
            containerSuccess.visible()
            loading.gone()
            txtUsername.text = user.username
            avatarProfile.load(user.avatarImg)
            txtLink.text = user.htmlUrl
            txtFollowers.text = "${user.followers} Seguidores"
            txtFollowing.text = "${user.following} Seguindo"
            txtRealName.text = user.realName
            txtCompany.text = user.company
            txtLocation.text = user.location
            txtRepoQuantity.text = user.publicRepos
            repoContainer.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_reposFragment)
            }
        }
    }

    private fun changeStatusBarColor() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.black
        )
    }

}
